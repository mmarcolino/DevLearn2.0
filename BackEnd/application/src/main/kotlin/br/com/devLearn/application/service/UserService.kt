package br.com.devLearn.application.service

import br.com.devLearn.application.controller.dtos.user.*
import br.com.devLearn.application.controller.mappers.user.*
import br.com.devLearn.application.model.User
import br.com.devLearn.application.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val repository: UserRepository,
                  private val updateMapper: UpdateUserMapper,
                  private val viewMapper: UserViewMapper,
                  private val viewListMapper: UserViewListMapper,
                  private val storeMapper: UserStoreMapper
) {

    fun listUsers(): List<UserViewListDto> {
        return viewListMapper.map(repository.findAll())
    }

    fun getUserById(id: Long): UserViewDto {
        return viewMapper.map(repository.getById(id))
    }

    fun updateUser(id: Long, dto: UpdateUserDto){
        val finalUser: User = repository.getById(id)
        updateMapper.map(dto, finalUser)
        repository.save(finalUser)
    }
    fun userSignUp(dto: UserStoreDto){
        repository.save(storeMapper.map(dto))
    }

    fun deleteUser(id: Long){
        repository.deleteById(id)
    }

}