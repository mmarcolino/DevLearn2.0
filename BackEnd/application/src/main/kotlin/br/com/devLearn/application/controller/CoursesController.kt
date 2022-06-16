package br.com.devLearn.application.controller

import br.com.devLearn.application.controller.dtos.courses.*
import br.com.devLearn.application.controller.mappers.courses.*
import br.com.devLearn.application.service.CourseService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/courses")
class CoursesController(
    private val service: CourseService,
    private val updateMapper: UpdateCoursesMapper,
    private val viewMapper: CoursesViewMapper,
    private val viewListMapper: CoursesViewListMapper,
    private val storeMapper: StoreCoursesMapper
) {

    @GetMapping
    fun listCourses(@RequestParam(required = false) categorieName: String?): List<CoursesViewDto>{
        return viewListMapper.map(service.listCourses(categorieName))
    }

    @GetMapping("{id}")
    fun findById(@PathVariable id: Long): CoursesViewDto{
        return viewMapper.map(service.getCourseById(id))
    }

    @PostMapping
    fun saveCourse(@RequestBody @Valid dto: StoreCoursesDto,
                   uriBuilder: UriComponentsBuilder): ResponseEntity<CoursesViewDto>{
        val courseView = viewMapper.map(service.storeCourse(storeMapper.map(dto)))
        val uri = uriBuilder.path("/course/${courseView.id}").build().toUri()
        return ResponseEntity.created(uri).body(courseView)
    }

    @PutMapping("{id}")
    fun updateCourse(@PathVariable id: Long,
        @RequestBody @Valid course: UpdateCoursesDto): ResponseEntity<CoursesViewDto>{
        val courseView = viewMapper.map(service.storeCourse(updateMapper.map(course, service.getCourseById(id))))
        return ResponseEntity.ok(courseView)
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCourse(@PathVariable id: Long){
        service.deleteCourse(id)
    }
}