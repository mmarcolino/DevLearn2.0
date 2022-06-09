package br.com.devLearn.application.controller.mappers.courses

import br.com.devLearn.application.controller.dtos.courses.CoursesViewDto
import br.com.devLearn.application.controller.mappers.DefaultMapper
import br.com.devLearn.application.model.Courses
import org.springframework.stereotype.Component

@Component
class CoursesViewListMapper: DefaultMapper<List<Courses>, List<CoursesViewDto>> {
    override fun map(courseList: List<Courses>): List<CoursesViewDto> {
        return courseList.map { Courses -> CoursesViewDto(
            id = Courses.id!!,
            name = Courses.name,
            description = Courses.description,
            authorId = Courses.author.id!!,
            categoryId = Courses.categories.id!!
        ) }
    }

}