package br.com.devLearn.application.service

import br.com.devLearn.application.excpetion.NotFoundException
import br.com.devLearn.application.model.Course
import br.com.devLearn.application.repository.CourseRepository
import org.springframework.stereotype.Service

@Service
class CourseService(private val repository: CourseRepository) {

    fun listCourses(categorieName: String?): List <Course>{
        val courses: List<Course> = if (categorieName.isNullOrBlank())
            repository.findAll()
        else
            repository.findByCategoriesName(categorieName)
        return courses
    }

    fun getCourseById(id: Long): Course{
        return repository.findById(id).orElseThrow { NotFoundException("Curso não encontrado") }
    }

    fun storeCourse(course: Course):Course{
        repository.save(course)
        return course
    }

    fun deleteCourse(id: Long){
        repository.deleteById(id)
    }
}