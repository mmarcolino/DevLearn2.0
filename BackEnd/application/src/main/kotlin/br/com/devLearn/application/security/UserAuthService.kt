package br.com.devLearn.application.security

import br.com.devLearn.application.excpetion.NotFoundException
import br.com.devLearn.application.model.User
import br.com.devLearn.application.repository.UserRepository
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class UserAuthService(private val repository: UserRepository): UserDetailsService{
    override fun loadUserByUsername(username: String): UserDetails {
        val user = repository.findByUsername(username)
        return CustomUserDetails(user)
    }
}