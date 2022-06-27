package br.com.devLearn.application.controller.mappers.course

import br.com.devLearn.application.controller.dtos.course.CourseViewDto
import br.com.devLearn.application.controller.mappers.DefaultMapper
import br.com.devLearn.application.model.Course
import org.springframework.stereotype.Component

@Component
class CourseViewListMapper: DefaultMapper<List<Course>, List<CourseViewDto>> {
    override fun map(courseList: List<Course>): List<CourseViewDto> {
        return courseList.map { Courses -> CourseViewDto(
            id = Courses.id!!,
            name = Courses.name,
            description = Courses.description,
            authorId = Courses.author.id!!,
            categoryId = Courses.category.id!!
        ) }
    }

}