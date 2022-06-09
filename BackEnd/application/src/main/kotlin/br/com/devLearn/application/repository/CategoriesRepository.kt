package br.com.devLearn.application.repository

import br.com.devLearn.application.model.Categories
import org.springframework.data.jpa.repository.JpaRepository

interface CategoriesRepository: JpaRepository<Categories, Long> {
}