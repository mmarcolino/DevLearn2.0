package br.com.devLearn.application.controller.mappers.user

import br.com.devLearn.application.controller.dtos.user.StoreUserDto
import br.com.devLearn.application.controller.mappers.DefaultMapper
import br.com.devLearn.application.model.User
import org.springframework.stereotype.Component

@Component
class StoreUserMapper:DefaultMapper<StoreUserDto, User> {
    override fun map(dto: StoreUserDto): User {
        return User(
            username = dto.username,
            password = dto.password,
            name = dto.name
        )
    }

}
