package br.com.devLearn.application.controller.mappers.courses

import br.com.devLearn.application.controller.dtos.courses.UpdateCoursesDto
import br.com.devLearn.application.controller.mappers.UpdateMapper
import br.com.devLearn.application.model.Courses
import br.com.devLearn.application.service.CategoriesService
import org.springframework.stereotype.Component

@Component
class UpdateCoursesMapper(private val categoriesService: CategoriesService): UpdateMapper<UpdateCoursesDto, Courses> {
    override fun map(dto: UpdateCoursesDto, course: Courses): Courses {

        if(!dto.name.isNullOrBlank()){
            course.name = dto.name!!
        }
        if(!dto.description.isNullOrBlank()){
            course.description == dto.description
        }
        if(dto.categoryId != null){
            course.categories = categoriesService.getCategoryById(dto.categoryId!!)
        }
        return course
    }
}