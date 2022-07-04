package br.com.devLearn.application.service

import br.com.devLearn.application.excpetion.NotFoundException
import br.com.devLearn.application.model.User
import br.com.devLearn.application.repository.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.core.userdetails.User as UserBuilder
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository): UserDetailsService {

    @Bean
    private fun passwordEncoder(): BCryptPasswordEncoder{
        return BCryptPasswordEncoder()
    }

    fun listUsers(): List<User> {
        return userRepository.findAll()
    }

    fun getUserById(id: Long): User {
        return userRepository.findById(id).orElseThrow {NotFoundException("Usuario não econtrado")}
    }

    fun getByUsername(username: String): User {
        return userRepository.findByUsername(username)?: throw NotFoundException("Usuario não econtrado")
    }

    fun storeUser(user: User): User{
        val passwordEncoder = passwordEncoder()
        user.password = passwordEncoder.encode(user.password)
        userRepository.save(user)
        return user
    }

    fun deleteUser(id: Long){
        userRepository.deleteById(id)
    }

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username)?: throw NotFoundException("Usuario não econtrado")
        val roles = user.roles.map { role -> GrantedAuthority { String.format("ROLE_%s_%s", role.name) } }
        return UserBuilder.
        withUsername(user.username).
        password(user.password).
        authorities(roles).
        build()
    }

}