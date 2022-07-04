package br.com.devLearn.application.controller.mappers.course

import br.com.devLearn.application.controller.dtos.course.UpdateCourseDto
import br.com.devLearn.application.controller.mappers.UpdateMapper
import br.com.devLearn.application.model.Course
import br.com.devLearn.application.service.CategoryService
import org.springframework.stereotype.Component

@Component
class UpdateCourseMapper(private val categoryService: CategoryService): UpdateMapper<UpdateCourseDto, Course> {
    override fun map(dto: UpdateCourseDto, course: Course): Course {

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