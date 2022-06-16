package br.com.devLearn.application.controller.mappers.courses

import br.com.devLearn.application.controller.dtos.courses.StoreCoursesDto
import br.com.devLearn.application.controller.mappers.DefaultMapper
import br.com.devLearn.application.model.Course
import br.com.devLearn.application.service.CategoryService
import br.com.devLearn.application.service.UserService
import org.springframework.stereotype.Component

@Component
class StoreCoursesMapper(private val userService: UserService,
                         private val categoryService: CategoryService):DefaultMapper<StoreCoursesDto, Course> {
    override fun map(dto: StoreCoursesDto): Course {
        return Course(
            name = dto.name,
            description = dto.description,
            author = userService.getUserById(dto.authorId),
            category = categoryService.getCategoryById(dto.categoryId)
        )
    }

}