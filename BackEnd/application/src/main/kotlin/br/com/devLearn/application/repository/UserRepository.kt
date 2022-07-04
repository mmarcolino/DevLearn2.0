package br.com.devLearn.application.repository

import br.com.devLearn.application.model.User
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository: JpaRepository<User, Long> {
    fun findByUsername(username: String): User
}