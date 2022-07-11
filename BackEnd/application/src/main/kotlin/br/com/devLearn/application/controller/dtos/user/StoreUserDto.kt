package br.com.devLearn.application.controller.dtos.user

import br.com.devLearn.application.model.Role
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

data class StoreUserDto (
    @field:NotEmpty
    @field:Size(min = 2, max = 15)
    var username: String,
    @field:NotEmpty
    @field:Size(min = 8)
    var password: String,
    @field:NotEmpty
    var name: String,
    @field:NotEmpty
    val roles: List<String>
)
