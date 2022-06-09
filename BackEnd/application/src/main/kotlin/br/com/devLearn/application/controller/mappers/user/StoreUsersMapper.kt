package br.com.devLearn.application.controller.mappers.user

import br.com.devLearn.application.controller.dtos.user.StoreUsersDto
import br.com.devLearn.application.controller.mappers.DefaultMapper
import br.com.devLearn.application.model.Users
import org.springframework.stereotype.Component

@Component
class StoreUsersMapper:DefaultMapper<StoreUsersDto, Users> {
    override fun map(dto: StoreUsersDto): Users {
        return Users(
            username = dto.username,
            password = dto.password,
            name = dto.name
        )
    }

}
