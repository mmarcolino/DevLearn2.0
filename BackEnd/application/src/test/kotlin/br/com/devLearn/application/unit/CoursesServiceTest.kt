package br.com.devLearn.application.unit

import br.com.devLearn.application.model.Categories
import br.com.devLearn.application.model.Courses
import br.com.devLearn.application.model.Users
import br.com.devLearn.application.repository.CoursesRepository
import br.com.devLearn.application.service.CoursesService
import io.mockk.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.random.Random

class CoursesServiceTest {

    private val coursesRepository: CoursesRepository = mockk(relaxed = true)
    private val coursesService = CoursesService(coursesRepository)
    private val authorEntity = Users(1, "Kenma123", "12345678", "Kenma")
    private val categoriesEntity = Categories(1, "Backend")
    private val mockedCourse = Courses(Random.nextLong(0, Long.MAX_VALUE), "Spring Boot", "Curso de Spring Boot no Kotlin", authorEntity, categoriesEntity)
    private val coursesId = this.mockedCourse.id

    @Test
    fun `it should return all registred Courses`(){
        //given
        every { coursesRepository.findAll() } returns emptyList()
        //when
        val result = coursesService.listCourse()
        //then
        verify (exactly = 1){ coursesRepository.findAll() }
    }

    @Test
    fun `it should return the registred course by id`(){
        //given
        every { coursesRepository.findById(coursesId!!) } returns Optional.of(mockedCourse)
        //when
        val result = coursesService.getCourseById(coursesId!!)
        //then
        Assertions.assertEquals(mockedCourse.id, result.id)
        Assertions.assertEquals(mockedCourse.name, result.name)
        Assertions.assertEquals(mockedCourse.description, result.description)
        Assertions.assertEquals(mockedCourse.author, result.author)
        Assertions.assertEquals(mockedCourse.categories, result.categories)

    }

    @Test
    fun `it should store and return a course from the database`(){
        //given
        val newCourse = Courses(
            id = mockedCourse.id,
            name = mockedCourse.name,
            description = mockedCourse.description,
            author = mockedCourse.author,
            categories = mockedCourse.categories
        )
        every { coursesRepository.save(any()) } returns mockedCourse
        //when
        val result = coursesService.storeCourse(newCourse)
        //then
        Assertions.assertEquals(mockedCourse.id, result.id)
        Assertions.assertEquals(mockedCourse.name, result.name)
        Assertions.assertEquals(mockedCourse.description, result.description)
        Assertions.assertEquals(mockedCourse.author, result.author)
        Assertions.assertEquals(mockedCourse.categories, result.categories)
        verify(exactly = 1){coursesRepository.save(any())}
    }

    @Test
    fun `it should delete a course from the database`(){
        //given
        every { coursesRepository.deleteById(coursesId!!) }just Runs
        //when
        coursesService.deleteCourse(coursesId!!)
        //then
        verify (exactly = 1){ coursesRepository.deleteById(coursesId) }
    }
}