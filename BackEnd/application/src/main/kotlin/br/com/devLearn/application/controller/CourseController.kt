package br.com.devLearn.application.controller

import br.com.devLearn.application.controller.dtos.courses.*
import br.com.devLearn.application.controller.mappers.courses.*
import br.com.devLearn.application.service.CoursesService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/course")
class CourseController(
    private val service: CoursesService,
    private val updateMapper: UpdateCourseMapper,
    private val viewMapper: CourseViewMapper,
    private val viewListMapper: CourseViewListMapper,
    private val storeMapper: CourseStoreMapper
) {

    @GetMapping
    fun listCourses(): List<CourseViewDto>{
        return viewListMapper.map(service.listCourse())
    }

    @GetMapping("{id}")
    fun findById(@PathVariable id: Long): CourseViewDto{
        return viewMapper.map(service.getCourseById(id))
    }

    @PostMapping
    fun saveCourse(@RequestBody @Valid dto: CourseStoreDto,
               uriBuilder: UriComponentsBuilder): ResponseEntity<CourseViewDto>{
        val courseView = viewMapper.map(service.storeCourse(storeMapper.map(dto)))
        val uri = uriBuilder.path("/course/${courseView.id}").build().toUri()
        return ResponseEntity.created(uri).body(courseView)
    }

    @PutMapping("{id}")
    fun updateCourse(@PathVariable id: Long,
        @RequestBody @Valid course: UpdateCourseDto): ResponseEntity<CourseViewDto>{
        val courseView = viewMapper.map(service.storeCourse(updateMapper.map(course, service.getCourseById(id))))
        return ResponseEntity.ok(courseView)
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCourse(@PathVariable id: Long){
        service.deleteCourse(id)
    }
}