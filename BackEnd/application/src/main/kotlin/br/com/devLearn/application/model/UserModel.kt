package br.com.devLearn.application.model

data class UserModel(
    val id: Long? = null,
    val username: String,
    val password: String,
    val name: String,
)

