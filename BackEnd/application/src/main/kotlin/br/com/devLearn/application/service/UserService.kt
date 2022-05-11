package br.com.devLearn.application.service

import br.com.devLearn.application.model.User
import br.com.devLearn.application.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val repository: UserRepository) {

    fun getUserById(id: Long): User{
        return repository.getById(id)
    }
}