package br.com.devLearn.application.controller.mappers.user

import br.com.devLearn.application.controller.dtos.user.UsersViewDto
import br.com.devLearn.application.controller.mappers.DefaultMapper
import br.com.devLearn.application.model.Users
import org.springframework.stereotype.Component

@Component
class UsersViewListMapper: DefaultMapper<List<Users>, List<UsersViewDto>> {

    override fun map(usersList: List<Users>): List<UsersViewDto> {
        return usersList.map { User -> UsersViewDto(
            id = User.id!!,
            username = User.username,
            password = User.password,
            name = User.name
        ) }
    }

}