package br.com.devLearn.application.controller

import br.com.devLearn.application.controller.dtos.user.UpdateUserDto
import br.com.devLearn.application.controller.dtos.user.UserStoreDto
import br.com.devLearn.application.controller.dtos.user.UserViewDto
import br.com.devLearn.application.controller.dtos.user.UserViewListDto
import br.com.devLearn.application.service.UserService
import org.springframework.web.bind.annotation.*
import javax.transaction.Transactional

@RestController
@RequestMapping("/user")
class UserController(private val service: UserService) {

    @GetMapping
    fun listUsers(): List<UserViewListDto> {
        return service.listUsers()
    }

    @GetMapping("{id}")
    fun findById(@PathVariable id: Long): UserViewDto {
        return service.getUserById(id)
    }

    @PostMapping
    @Transactional
    fun singUp(@RequestBody dto: UserStoreDto) {
        service.userSignUp(dto)
    }

    @PutMapping("{id}")
    @Transactional
    fun updateUser(@PathVariable id: Long, @RequestBody user: UpdateUserDto){
        service.updateUser(id, user)
    }

    @DeleteMapping("{id}")
    @Transactional
    fun deleteUser(@PathVariable id: Long){
        service.deleteUser(id)
    }

}