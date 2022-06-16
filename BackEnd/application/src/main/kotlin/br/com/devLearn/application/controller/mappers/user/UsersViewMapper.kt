package br.com.devLearn.application.controller.mappers.user

import br.com.devLearn.application.controller.dtos.user.UsersViewDto
import br.com.devLearn.application.controller.mappers.DefaultMapper
import br.com.devLearn.application.model.User
import org.springframework.stereotype.Component

@Component
class UsersViewMapper: DefaultMapper<User, UsersViewDto> {
    override fun map(user: User): UsersViewDto {
        return UsersViewDto(
            id = user.id!!,
            username = user.username,
            password = user.password,
            name = user.name
        )
    }
}