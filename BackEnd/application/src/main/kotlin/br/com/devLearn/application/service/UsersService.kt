package br.com.devLearn.application.service

import br.com.devLearn.application.excpetion.NotFoundException
import br.com.devLearn.application.model.Users
import br.com.devLearn.application.repository.UsersRepository
import org.springframework.stereotype.Service

@Service
class UsersService(private val repository: UsersRepository,
                   private  val NOT_FOUND_MESSAGE: String = "Usuario não econtrado") {

    fun listUsers(): List<Users> {
        return repository.findAll()
    }

    fun getUserById(id: Long): Users {
        return repository.findById(id).orElseThrow {NotFoundException(NOT_FOUND_MESSAGE)}
    }


    fun storeUser(users: Users): Users{
        repository.save(users)
        return users
    }

    fun deleteUser(id: Long){
        repository.deleteById(id)
    }

}