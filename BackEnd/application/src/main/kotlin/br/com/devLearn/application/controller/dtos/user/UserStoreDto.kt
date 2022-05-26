package br.com.devLearn.application.controller.dtos.user

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Size

data class UserStoreDto (
    @field:NotEmpty
    @field:Size(min = 2, max = 15)
    var username: String,
    @field:NotEmpty
    @field:Size(min = 8, max = 16)
    var password: String,
    @field:NotEmpty
    var name: String,
)
