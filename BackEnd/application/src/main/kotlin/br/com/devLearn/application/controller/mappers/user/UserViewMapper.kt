package br.com.devLearn.application.controller.mappers.user

import br.com.devLearn.application.controller.dtos.user.UserViewDto
import br.com.devLearn.application.controller.mappers.DefaultMapper
import br.com.devLearn.application.model.User
import org.springframework.stereotype.Component

@Component
class UserViewMapper: DefaultMapper<User, UserViewDto> {
    override fun map(user: User): UserViewDto {
        return UserViewDto(
            id = user.id!!,
            username = user.username,
            password = user.password,
            name = user.name
        )
    }
}