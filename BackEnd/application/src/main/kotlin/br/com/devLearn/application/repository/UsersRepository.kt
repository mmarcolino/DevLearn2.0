package br.com.devLearn.application.repository

import br.com.devLearn.application.model.Users
import org.springframework.data.jpa.repository.JpaRepository

interface UsersRepository: JpaRepository<Users, Long> {
}