package br.com.devLearn.application.controller.mappers.user

import br.com.devLearn.application.controller.dtos.user.UsersViewDto
import br.com.devLearn.application.controller.mappers.DefaultMapper
import br.com.devLearn.application.model.Users
import org.springframework.stereotype.Component

@Component
class UsersViewMapper: DefaultMapper<Users, UsersViewDto> {
    override fun map(users: Users): UsersViewDto {
        return UsersViewDto(
            id = users.id!!,
            username = users.username,
            password = users.password,
            name = users.name
        )
    }
}