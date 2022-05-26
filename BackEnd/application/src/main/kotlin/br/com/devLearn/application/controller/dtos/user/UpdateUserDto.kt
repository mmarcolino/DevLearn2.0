package br.com.devLearn.application.controller.dtos.user

import javax.validation.constraints.Size

data class UpdateUserDto (
    @field:Size(min = 8, max = 16)
    var username: String?,
    @field:Size(min = 8, max = 16)
    var password: String?,
)