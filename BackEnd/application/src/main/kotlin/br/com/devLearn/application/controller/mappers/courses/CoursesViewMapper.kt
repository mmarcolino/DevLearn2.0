package br.com.devLearn.application.controller.mappers.courses

import br.com.devLearn.application.controller.dtos.courses.CoursesViewDto
import br.com.devLearn.application.controller.mappers.DefaultMapper
import br.com.devLearn.application.model.Courses
import org.springframework.stereotype.Component

@Component
class CoursesViewMapper: DefaultMapper<Courses, CoursesViewDto> {
    override fun map(course: Courses): CoursesViewDto {
        return CoursesViewDto(
            id = course.id!!,
            name = course.name,
            description = course.description,
            authorId = course.author.id!!,
            categoryId = course.categories.id!!
        )
    }

}