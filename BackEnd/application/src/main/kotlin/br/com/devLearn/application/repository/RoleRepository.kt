package br.com.devLearn.application.repository

import br.com.devLearn.application.model.Role
import org.springframework.data.jpa.repository.JpaRepository

interface RoleRepository:JpaRepository<Role, Long> {
    fun findByName(name: String): Role?
}