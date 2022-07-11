package br.com.devLearn.application.controller.mappers.user

import br.com.devLearn.application.controller.dtos.user.UserViewDto
import br.com.devLearn.application.controller.mappers.DefaultMapper
import br.com.devLearn.application.model.User
import org.springframework.stereotype.Component

@Component
class UserViewListMapper: DefaultMapper<List<User>, List<UserViewDto>> {

    override fun map(userList: List<User>): List<UserViewDto> {
        return userList.map { User -> UserViewDto(
            id = User.id!!,
            username = User.username,
            name = User.name,
            roles = User.roles.map { role -> role.name }
        ) }
    }

}