package br.com.devLearn.application.controller.mappers.user

import br.com.devLearn.application.controller.dtos.user.UserViewListDto
import br.com.devLearn.application.controller.mappers.DefaultMapper
import br.com.devLearn.application.model.User
import org.springframework.stereotype.Component

@Component
class UserViewListMapper: DefaultMapper<List<User>, List<UserViewListDto>> {

    override fun map(userList: List<User>): List<UserViewListDto> {
        return userList.map { User -> UserViewListDto(
            id = User.id!!,
            username = User.username,
            password = User.password,
            name = User.name
        ) }
    }

}