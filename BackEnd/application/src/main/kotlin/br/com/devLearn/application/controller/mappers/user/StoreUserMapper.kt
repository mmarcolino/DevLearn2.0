package br.com.devLearn.application.controller.mappers.user

import br.com.devLearn.application.controller.dtos.user.StoreUserDto
import br.com.devLearn.application.controller.mappers.DefaultMapper
import br.com.devLearn.application.excpetion.NotFoundException
import br.com.devLearn.application.model.Role
import br.com.devLearn.application.model.User
import br.com.devLearn.application.repository.RoleRepository
import org.springframework.stereotype.Component

@Component
class StoreUserMapper(private val roleRepository: RoleRepository):DefaultMapper<StoreUserDto, User> {
    override fun map(dto: StoreUserDto): User {
        val roles = dto.roles.map { name -> roleRepository.findByName(name)?: throw NotFoundException("Role não encontrado")}
        return User(
            username = dto.username,
            password = dto.password,
            name = dto.name,
            roles =  roles
        )
    }

}
