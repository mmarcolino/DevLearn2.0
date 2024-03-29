package br.com.devLearn.application.controller

import br.com.devLearn.application.controller.dtos.course.*
import br.com.devLearn.application.controller.mappers.course.*
import br.com.devLearn.application.service.CourseService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/courses")
class CourseController(
    private val service: CourseService,
    private val updateMapper: UpdateCourseMapper,
    private val viewMapper: CourseViewMapper,
    private val viewListMapper: CourseViewListMapper,
    private val storeMapper: StoreCourseMapper
) {

    @PreAuthorize("hasRole('ROLE_VIEWER') or hasRole('CONTENT_CREATOR') or hasRole('ROLE_ADMIN')")
    @GetMapping
    fun listCourses(@RequestParam(required = false) categorieName: String?): List<CourseViewDto>{
        return viewListMapper.map(service.listCourses(categorieName))
    }

    @PreAuthorize("hasRole('ROLE_VIEWER') or hasRole('CONTENT_CREATOR') or hasRole('ROLE_ADMIN')")
    @GetMapping("{id}")
    fun findById(@PathVariable id: Long): CourseViewDto{
        return viewMapper.map(service.getCourseById(id))
    }

    @PreAuthorize("hasRole('CONTENT_CREATOR') or hasRole('ROLE_ADMIN')")
    @PostMapping
    fun saveCourse(@RequestBody @Valid dto: StoreCourseDto,
                   uriBuilder: UriComponentsBuilder): ResponseEntity<CourseViewDto>{
        val courseView = viewMapper.map(service.storeCourse(storeMapper.map(dto)))
        val uri = uriBuilder.path("/course/${courseView.id}").build().toUri()
        return ResponseEntity.created(uri).body(courseView)
    }

    @PreAuthorize("hasRole('CONTENT_CREATOR') or hasRole('ROLE_ADMIN')")
    @PutMapping("{id}")
    fun updateCourse(@PathVariable id: Long,
        @RequestBody @Valid course: UpdateCourseDto): ResponseEntity<CourseViewDto>{
        val courseView = viewMapper.map(service.storeCourse(updateMapper.map(course, service.getCourseById(id))))
        return ResponseEntity.ok(courseView)
    }

    @PreAuthorize("hasRole('CONTENT_CREATOR') or  hasRole('ROLE_ADMIN')")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCourse(@PathVariable id: Long){
        service.deleteCourse(id)
    }
}