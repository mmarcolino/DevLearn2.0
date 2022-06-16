package br.com.devLearn.application.controller.mappers.courses

import br.com.devLearn.application.controller.dtos.courses.UpdateCoursesDto
import br.com.devLearn.application.controller.mappers.UpdateMapper
import br.com.devLearn.application.model.Course
import br.com.devLearn.application.service.CategoryService
import org.springframework.stereotype.Component

@Component
class UpdateCoursesMapper(private val categoryService: CategoryService): UpdateMapper<UpdateCoursesDto, Course> {
    override fun map(dto: UpdateCoursesDto, course: Course): Course {

        if(!dto.name.isNullOrBlank()){
            course.name = dto.name!!
        }
        if(!dto.description.isNullOrBlank()){
            course.description == dto.description
        }
        if(dto.categoryId != null){
            course.category = categoryService.getCategoryById(dto.categoryId!!)
        }
        return course
    }
}