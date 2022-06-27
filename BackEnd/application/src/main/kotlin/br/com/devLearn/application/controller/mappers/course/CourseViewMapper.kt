package br.com.devLearn.application.controller.mappers.course

import br.com.devLearn.application.controller.dtos.course.CourseViewDto
import br.com.devLearn.application.controller.mappers.DefaultMapper
import br.com.devLearn.application.model.Course
import org.springframework.stereotype.Component

@Component
class CourseViewMapper: DefaultMapper<Course, CourseViewDto> {
    override fun map(course: Course): CourseViewDto {
        return CourseViewDto(
            id = course.id!!,
            name = course.name,
            description = course.description,
            authorId = course.author.id!!,
            categoryId = course.category.id!!
        )
    }

}