package br.com.devLearn.application.controller.mappers.user

import br.com.devLearn.application.controller.dtos.user.UserStoreDto
import br.com.devLearn.application.controller.mappers.DefaultMapper
import br.com.devLearn.application.model.User
import org.springframework.stereotype.Component

@Component
class UserStoreMapper:DefaultMapper<UserStoreDto, User> {
    override fun map(dto: UserStoreDto): User {
        return User(
            username = dto.username,
            password = dto.password,
            name = dto.name
        )
    }

}
