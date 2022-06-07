package br.com.devLearn.application.controller.mappers.courses

import br.com.devLearn.application.controller.dtos.courses.CourseStoreDto
import br.com.devLearn.application.controller.mappers.DefaultMapper
import br.com.devLearn.application.model.Courses
import br.com.devLearn.application.service.CategoryService
import br.com.devLearn.application.service.UserService
import org.springframework.stereotype.Component

@Component
class CourseStoreMapper( private val userService: UserService,
                         private val categoryService: CategoryService):DefaultMapper<CourseStoreDto, Courses> {
    override fun map(dto: CourseStoreDto): Courses {
        return Courses(
            name = dto.name,
            description = dto.description,
            author = userService.getUserById(dto.authorId),
            category = categoryService.getCategoryById(dto.categoryId)
        )
    }

}