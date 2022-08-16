package br.com.devLearn.application.service

import br.com.devLearn.application.excpetion.NotFoundException
import br.com.devLearn.application.model.User
import br.com.devLearn.application.repository.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.core.userdetails.User as UserBuilder
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class UserService(private val userRepository: UserRepository): UserDetailsService {

    @Bean
    fun bCryptpasswordEncoder(): PasswordEncoder {
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
        user.password = bCryptpasswordEncoder().encode(user.password)
        userRepository.save(user)
        return user
    }

    fun deleteUser(id: Long){
        userRepository.deleteById(id)
    }

    @Transactional
    override fun loadUserByUsername(username: String): UserDetails {
        val user = userRepository.findByUsername(username)?: throw NotFoundException("Usuario não econtrado")
        val roles = user.roles.map { role -> "ROLE_${role.name}" }
        val authorities = roles.map { role -> GrantedAuthority { role } }
        return UserBuilder.
        withUsername(user.username).
        password(user.password).
        authorities(authorities).
        build()
    }

}