package br.com.devLearn.application.service

import br.com.devLearn.application.excpetion.NotFoundException
import br.com.devLearn.application.model.User
import br.com.devLearn.application.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val repository: UserRepository,
                  private  val NOT_FOUND_MESSAGE: String = "Usuario não econtrado") {

    fun listUsers(): List<User> {
        return repository.findAll()
    }

    fun getUserById(id: Long): User {
        return repository.findById(id).orElseThrow {NotFoundException(NOT_FOUND_MESSAGE)}
    }


    fun storeUser(user: User): User{
        repository.save(user)
        return user
    }

    fun deleteUser(id: Long){
        repository.deleteById(id)
    }

}