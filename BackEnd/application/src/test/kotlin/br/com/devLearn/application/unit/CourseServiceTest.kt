package br.com.devLearn.application.unit

import br.com.devLearn.application.model.Category
import br.com.devLearn.application.model.Course
import br.com.devLearn.application.model.User
import br.com.devLearn.application.repository.CourseRepository
import br.com.devLearn.application.service.CourseService
import io.mockk.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.random.Random

class CourseServiceTest {

    private val courseRepository: CourseRepository = mockk(relaxed = true)
    private val courseService = CourseService(courseRepository)
    private val authorEntity = User(1, "Kenma123", "12345678", "Kenma")
    private val categoryEntity = Category(1, "Backend")
    private val mockedCourse = Course(Random.nextLong(0, Long.MAX_VALUE), "Spring Boot", "Curso de Spring Boot no Kotlin", authorEntity, categoryEntity)
    private val coursesId = this.mockedCourse.id

    @Test
    fun `it should return all registred Courses`(){
        //given
        every { courseRepository.findAll() } returns emptyList()
        //when
        val result = courseService.listCourses("")
        //then
        verify (exactly = 1){ courseRepository.findAll() }
    }

    @Test
    fun `it should return all registred Courses with categorie equal to x`(){
        //given
        val categorieName = "teste"
        every { courseRepository.findByCategoriesName(categorieName) } returns emptyList()
        //when
        val result = courseService.listCourses(categorieName)
        //then
        verify (exactly = 1){ courseRepository.findByCategoriesName(categorieName) }
    }

    @Test
    fun `it should return the registred course by id`(){
        //given
        every { courseRepository.findById(coursesId!!) } returns Optional.of(mockedCourse)
        //when
        val result = courseService.getCourseById(coursesId!!)
        //then
        Assertions.assertEquals(mockedCourse.id, result.id)
        Assertions.assertEquals(mockedCourse.name, result.name)
        Assertions.assertEquals(mockedCourse.description, result.description)
        Assertions.assertEquals(mockedCourse.author, result.author)
        Assertions.assertEquals(mockedCourse.category, result.category)

    }

    @Test
    fun `it should store and return a course from the database`(){
        //given
        val newCourse = Course(
            id = mockedCourse.id,
            name = mockedCourse.name,
            description = mockedCourse.description,
            author = mockedCourse.author,
            category = mockedCourse.category
        )
        every { courseRepository.save(any()) } returns mockedCourse
        //when
        val result = courseService.storeCourse(newCourse)
        //then
        Assertions.assertEquals(mockedCourse.id, result.id)
        Assertions.assertEquals(mockedCourse.name, result.name)
        Assertions.assertEquals(mockedCourse.description, result.description)
        Assertions.assertEquals(mockedCourse.author, result.author)
        Assertions.assertEquals(mockedCourse.category, result.category)
        verify(exactly = 1){courseRepository.save(any())}
    }

    @Test
    fun `it should delete a course from the database`(){
        //given
        every { courseRepository.deleteById(coursesId!!) }just Runs
        //when
        courseService.deleteCourse(coursesId!!)
        //then
        verify (exactly = 1){ courseRepository.deleteById(coursesId) }
    }
}