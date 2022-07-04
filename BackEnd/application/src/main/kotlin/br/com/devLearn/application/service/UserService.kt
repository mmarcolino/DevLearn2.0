package br.com.devLearn.application.service

import br.com.devLearn.application.excpetion.NotFoundException
import br.com.devLearn.application.model.User
import br.com.devLearn.application.repository.UserRepository
import org.springframework.context.annotation.Bean
import org.springframework.security.crypto.bcrypt.BCrypt
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class UserService(private val repository: UserRepository) {

    @Bean
    private fun passwordEncoder(): BCryptPasswordEncoder{
        return BCryptPasswordEncoder()
    }

    fun listUsers(): List<User> {
        return repository.findAll()
    }

    fun getUserById(id: Long): User {
        return repository.findById(id).orElseThrow {NotFoundException("Usuario não econtrado")}
    }

    fun getByUsername(username: String): User {
        return repository.findByUsername(username)
    }

    fun storeUser(user: User): User{
        val passwordEncoder = passwordEncoder()
        user.password = passwordEncoder.encode(user.password)
        repository.save(user)
        return user
    }

    fun deleteUser(id: Long){
        repository.deleteById(id)
    }

}