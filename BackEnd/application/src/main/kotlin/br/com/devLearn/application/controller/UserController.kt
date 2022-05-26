package br.com.devLearn.application.controller

import br.com.devLearn.application.controller.dtos.user.*
import br.com.devLearn.application.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.transaction.Transactional
import javax.validation.Valid

@RestController
@RequestMapping("/user")
class UserController(private val service: UserService) {

    @GetMapping
    fun listUsers(): List<UserViewDto> {
        return service.listUsers()
    }

    @GetMapping("{id}")
    fun findById(@PathVariable id: Long): UserViewDto {
        return service.getUserById(id)
    }

    @PostMapping
    @Transactional
    fun singUp(@RequestBody @Valid dto: UserStoreDto,
    uriBuilder: UriComponentsBuilder): ResponseEntity<UserViewDto>  {
        val userView = service.userSignUp(dto)
        val uri = uriBuilder.path("/user/${userView.id}").build().toUri()
        return ResponseEntity.created(uri).body(userView)
    }

    @PutMapping("{id}")
    @Transactional
    fun updateUser(@PathVariable id: Long,
                   @RequestBody @Valid user: UpdateUserDto): ResponseEntity<UserViewDto>{
        val userView = service.updateUser(id, user)
        return ResponseEntity.ok(userView)
    }

    @DeleteMapping("{id}")
    @Transactional
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUser(@PathVariable id: Long){
        service.deleteUser(id)
    }

}