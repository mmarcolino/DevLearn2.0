package br.com.devLearn.application.controller.mappers.course

import br.com.devLearn.application.controller.dtos.course.StoreCourseDto
import br.com.devLearn.application.controller.mappers.DefaultMapper
import br.com.devLearn.application.model.Course
import br.com.devLearn.application.service.CategoryService
import br.com.devLearn.application.service.UserService
import org.springframework.stereotype.Component

@Component
class StoreCourseMapper(private val userService: UserService,
                        private val categoryService: CategoryService):DefaultMapper<StoreCourseDto, Course> {
    override fun map(dto: StoreCourseDto): Course {
        return Course(
            name = dto.name,
            description = dto.description,
            author = userService.getUserById(dto.authorId),
            category = categoryService.getCategoryById(dto.categoryId)
        )
    }

}