package br.com.devLearn.application.controller

import br.com.devLearn.application.model.User
import br.com.devLearn.application.service.UserService
import org.springframework.web.bind.annotation.*
import javax.transaction.Transactional

@RestController
@RequestMapping("/user")
class UserController(private val service: UserService) {

    @GetMapping
    fun listUsers(): List<User>{
        return service.listUsers()
    }

    @GetMapping("{id}")
    fun findById(@PathVariable id: Long): User{
        return service.getUserById(id)
    }

    @PostMapping
    @Transactional
    fun singUp(@RequestBody user: User) {
        service.userSignUp(user)
    }

    @PutMapping
    @Transactional
    fun updateUser(@RequestBody user: User){
        service.updateUser(user)
    }

    @DeleteMapping("{id}")
    @Transactional
    fun deleteUser(@PathVariable id: Long){
        service.deleteUser(id)
    }

}