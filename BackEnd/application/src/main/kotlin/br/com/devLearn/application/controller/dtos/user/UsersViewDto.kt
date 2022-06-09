package br.com.devLearn.application.controller.dtos.user

data class UsersViewDto (
    var id: Long,
    var username: String,
    var password: String,
    var name: String,
)