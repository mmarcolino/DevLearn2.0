package br.com.devLearn.application.service

import br.com.devLearn.application.excpetion.NotFoundException
import br.com.devLearn.application.model.Courses
import br.com.devLearn.application.repository.CoursesRepository
import org.springframework.stereotype.Service

@Service
class CoursesService(private val repository: CoursesRepository,
                     private val NOT_FOUND_MESSAGE: String = "Curso não encontrado") {

    fun listCourse(): List <Courses>{
        return repository.findAll()
    }

    fun getCourseById(id: Long): Courses{
        return repository.findById(id).orElseThrow { NotFoundException(NOT_FOUND_MESSAGE) }
    }

    fun storeCourse(course: Courses):Courses{
        repository.save(course)
        return course
    }

    fun deleteCourse(id: Long){
        repository.deleteById(id)
    }
}