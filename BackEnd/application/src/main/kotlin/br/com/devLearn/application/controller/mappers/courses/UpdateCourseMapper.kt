package br.com.devLearn.application.controller.mappers.courses

import br.com.devLearn.application.controller.dtos.courses.UpdateCourseDto
import br.com.devLearn.application.controller.mappers.UpdateMapper
import br.com.devLearn.application.model.Courses
import br.com.devLearn.application.service.CategoryService
import org.springframework.stereotype.Component

@Component
class UpdateCourseMapper(private val categoryService: CategoryService): UpdateMapper<UpdateCourseDto, Courses> {
    override fun map(dto: UpdateCourseDto, course: Courses): Courses {

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