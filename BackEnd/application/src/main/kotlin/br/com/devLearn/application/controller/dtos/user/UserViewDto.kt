package br.com.devLearn.application.controller.dtos.user

data class UserViewDto (
    var id: Long,
    var username: String,
    var name: String,
    var roles: List<String>
)