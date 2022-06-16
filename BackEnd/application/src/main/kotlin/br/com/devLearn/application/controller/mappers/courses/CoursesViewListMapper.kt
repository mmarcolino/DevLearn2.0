package br.com.devLearn.application.controller.mappers.courses

import br.com.devLearn.application.controller.dtos.courses.CoursesViewDto
import br.com.devLearn.application.controller.mappers.DefaultMapper
import br.com.devLearn.application.model.Course
import org.springframework.stereotype.Component

@Component
class CoursesViewListMapper: DefaultMapper<List<Course>, List<CoursesViewDto>> {
    override fun map(courseList: List<Course>): List<CoursesViewDto> {
        return courseList.map { Courses -> CoursesViewDto(
            id = Courses.id!!,
            name = Courses.name,
            description = Courses.description,
            authorId = Courses.author.id!!,
            categoryId = Courses.category.id!!
        ) }
    }

}