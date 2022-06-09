package br.com.devLearn.application.controller.mappers.courses

import br.com.devLearn.application.controller.dtos.courses.StoreCoursesDto
import br.com.devLearn.application.controller.mappers.DefaultMapper
import br.com.devLearn.application.model.Courses
import br.com.devLearn.application.service.CategoriesService
import br.com.devLearn.application.service.UsersService
import org.springframework.stereotype.Component

@Component
class StoreCoursesMapper(private val usersService: UsersService,
                         private val categoriesService: CategoriesService):DefaultMapper<StoreCoursesDto, Courses> {
    override fun map(dto: StoreCoursesDto): Courses {
        return Courses(
            name = dto.name,
            description = dto.description,
            author = usersService.getUserById(dto.authorId),
            categories = categoriesService.getCategoryById(dto.categoryId)
        )
    }

}