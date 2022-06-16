package br.com.devLearn.application.controller.mappers.user

import br.com.devLearn.application.controller.dtos.user.UsersViewDto
import br.com.devLearn.application.controller.mappers.DefaultMapper
import br.com.devLearn.application.model.User
import org.springframework.stereotype.Component

@Component
class UsersViewListMapper: DefaultMapper<List<User>, List<UsersViewDto>> {

    override fun map(userList: List<User>): List<UsersViewDto> {
        return userList.map { User -> UsersViewDto(
            id = User.id!!,
            username = User.username,
            password = User.password,
            name = User.name
        ) }
    }

}