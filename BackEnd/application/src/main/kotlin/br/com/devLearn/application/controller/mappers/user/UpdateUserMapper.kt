package br.com.devLearn.application.controller.mappers.user

import br.com.devLearn.application.controller.dtos.user.UpdateUserDto
import br.com.devLearn.application.controller.mappers.UpdateMapper
import br.com.devLearn.application.model.User
import org.springframework.stereotype.Component

@Component
class UpdateUserMapper: UpdateMapper<UpdateUserDto, User> {
    override fun map(dto: UpdateUserDto, user: User) {
        if (!dto.username.isNullOrBlank()){
            user.username = dto.username!!
        }
        if (!dto.password.isNullOrBlank()){
            user.password = dto.password!!
        }
    }

}