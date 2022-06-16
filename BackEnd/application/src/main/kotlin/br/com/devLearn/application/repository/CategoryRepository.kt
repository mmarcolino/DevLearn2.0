package br.com.devLearn.application.repository

import br.com.devLearn.application.model.Category
import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository: JpaRepository<Category, Long> {
}