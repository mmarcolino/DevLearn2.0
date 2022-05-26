package br.com.devLearn.application.service

import br.com.devLearn.application.controller.dtos.user.*
import br.com.devLearn.application.controller.mappers.user.*
import br.com.devLearn.application.excpetion.NotFoundException
import br.com.devLearn.application.model.User
import br.com.devLearn.application.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val repository: UserRepository,
                  private val updateMapper: UpdateUserMapper,
                  private val viewMapper: UserViewMapper,
                  private val viewListMapper: UserViewListMapper,
                  private val storeMapper: UserStoreMapper,
                  private val NOT_FOUND_MESSAGE: String = "Usuario não econtrado"
) {

    fun listUsers(): List<UserViewDto> {
        return viewListMapper.map(repository.findAll())
    }

    fun getUserById(id: Long): UserViewDto {
        return viewMapper.map(repository.findById(id).orElseThrow {NotFoundException(NOT_FOUND_MESSAGE)})
    }

    fun updateUser(id: Long, dto: UpdateUserDto): UserViewDto{
        val finalUser: User = repository.findById(id).orElseThrow {NotFoundException(NOT_FOUND_MESSAGE)}
        updateMapper.map(dto, finalUser)
        repository.save(finalUser)
        return viewMapper.map(finalUser)
    }
    fun userSignUp(dto: UserStoreDto): UserViewDto{
        val user: User = storeMapper.map(dto)
        repository.save(user)
        return viewMapper.map(user)
    }

    fun deleteUser(id: Long){
        repository.deleteById(id)
    }

}