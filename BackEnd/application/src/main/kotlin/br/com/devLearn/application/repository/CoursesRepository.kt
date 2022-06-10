package br.com.devLearn.application.repository

import br.com.devLearn.application.model.Courses
import org.springframework.data.jpa.repository.JpaRepository

interface CoursesRepository:JpaRepository<Courses, Long> {
    fun findByCategoriesName(categorieName: String): List<Courses>
}