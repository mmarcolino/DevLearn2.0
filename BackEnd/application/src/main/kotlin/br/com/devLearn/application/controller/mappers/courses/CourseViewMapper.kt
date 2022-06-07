package br.com.devLearn.application.controller.mappers.courses

import br.com.devLearn.application.controller.dtos.courses.CourseViewDto
import br.com.devLearn.application.controller.mappers.DefaultMapper
import br.com.devLearn.application.model.Courses
import org.springframework.stereotype.Component

@Component
class CourseViewMapper: DefaultMapper<Courses, CourseViewDto> {
    override fun map(course: Courses): CourseViewDto {
        return CourseViewDto(
            id = course.id!!,
            name = course.name,
            description = course.description,
            authorId = course.author.id!!,
            categoryId = course.category.id!!
        )
    }

}