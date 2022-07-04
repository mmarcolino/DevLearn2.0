package br.com.devLearn.application.repository

import br.com.devLearn.application.model.Course
import org.springframework.data.jpa.repository.JpaRepository

interface CourseRepository:JpaRepository<Course, Long> {
    fun findByCategoryName(categorieName: String): List<Course>?
}