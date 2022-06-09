package br.com.devLearn.application.controller

import br.com.devLearn.application.controller.dtos.user.*
import br.com.devLearn.application.controller.mappers.user.*
import br.com.devLearn.application.service.UsersService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/users")
class UsersController(private val service: UsersService,
                      private val updateMapper: UpdateUsersMapper,
                      private val viewMapper: UsersViewMapper,
                      private val viewListMapper: UsersViewListMapper,
                      private val storeMapper: StoreUsersMapper) {

    @GetMapping
    fun listUsers(): List<UsersViewDto> {
        return viewListMapper.map(service.listUsers())
    }

    @GetMapping("{id}")
    fun findById(@PathVariable id: Long): UsersViewDto {
        return viewMapper.map(service.getUserById(id))
    }

    @PostMapping
    fun singUp(@RequestBody @Valid dto: StoreUsersDto,
               uriBuilder: UriComponentsBuilder): ResponseEntity<UsersViewDto>  {
        val userView = viewMapper.map(service.storeUser(storeMapper.map(dto)))
        val uri = uriBuilder.path("/user/${userView.id}").build().toUri()
        return ResponseEntity.created(uri).body(userView)
    }

    @PutMapping("{id}")
    fun updateUser(@PathVariable id: Long,
                   @RequestBody @Valid user: UpdateUserDto): ResponseEntity<UsersViewDto>{
        val userView = viewMapper.map(service.storeUser(updateMapper.map(user, service.getUserById(id))))
        return ResponseEntity.ok(userView)
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteUser(@PathVariable id: Long){
        service.deleteUser(id)
    }

}