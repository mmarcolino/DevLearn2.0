package br.com.devLearn.application.controller.mappers.user

import br.com.devLearn.application.controller.dtos.user.StoreUsersDto
import br.com.devLearn.application.controller.mappers.DefaultMapper
import br.com.devLearn.application.model.User
import org.springframework.stereotype.Component

@Component
class StoreUsersMapper:DefaultMapper<StoreUsersDto, User> {
    override fun map(dto: StoreUsersDto): User {
        return User(
            username = dto.username,
            password = dto.password,
            name = dto.name
        )
    }

}
