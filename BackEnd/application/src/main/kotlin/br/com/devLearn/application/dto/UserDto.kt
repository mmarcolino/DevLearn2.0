package br.com.devLearn.application.dto

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

data class UserDto(
    @field:NotNull
    val id: Long? = null,
    @field:NotEmpty
    @field:Size(min = 2, max = 16)
    val username: String,
    @field:NotEmpty
    @field:Size(min = 8, max = 16)
    val password: String,
    @field:NotEmpty
    @field:Size(min = 3, max = 40)
    val name: String,
)
