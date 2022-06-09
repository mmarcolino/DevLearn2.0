package br.com.devLearn.application.controller.mappers.user

import br.com.devLearn.application.controller.dtos.user.UpdateUserDto
import br.com.devLearn.application.controller.mappers.UpdateMapper
import br.com.devLearn.application.model.Users
import org.springframework.stereotype.Component

@Component
class UpdateUsersMapper: UpdateMapper<UpdateUserDto, Users> {
    override fun map(dto: UpdateUserDto, users: Users):Users {
        if (!dto.username.isNullOrBlank()){
            users.username = dto.username!!
        }
        if (!dto.password.isNullOrBlank()){
            users.password = dto.password!!
        }
        return users
    }

}