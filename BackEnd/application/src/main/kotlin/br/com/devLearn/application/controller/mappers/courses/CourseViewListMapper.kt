package br.com.devLearn.application.controller.mappers.courses

import br.com.devLearn.application.controller.dtos.courses.CourseViewDto
import br.com.devLearn.application.controller.mappers.DefaultMapper
import br.com.devLearn.application.model.Courses
import org.springframework.stereotype.Component

@Component
class CourseViewListMapper: DefaultMapper<List<Courses>, List<CourseViewDto>> {
    override fun map(courseList: List<Courses>): List<CourseViewDto> {
        return courseList.map { Courses -> CourseViewDto(
            id = Courses.id!!,
            name = Courses.name,
            description = Courses.description,
            authorId = Courses.author.id!!,
            categoryId = Courses.category.id!!
        ) }
    }

}