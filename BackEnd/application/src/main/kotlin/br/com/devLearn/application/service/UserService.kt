package br.com.devLearn.application.service

import br.com.devLearn.application.model.User
import br.com.devLearn.application.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val repository: UserRepository) {

    fun listUsers(): List<User>{
        return repository.findAll()
    }

    fun getUserById(id: Long): User{
        return repository.getById(id)
    }

    fun updateUser(newUser: User){
        val finalUser = User(
            newUser.id,
            newUser.username,
            newUser.password,
            newUser.name
        )
        repository.save(finalUser)
    }
    fun userSignUp(user: User){
        repository.save(user)
    }

    fun deleteUser(id: Long){
        repository.deleteById(id)
    }


}