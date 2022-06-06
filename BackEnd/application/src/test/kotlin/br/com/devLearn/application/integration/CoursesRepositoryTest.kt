package br.com.devLearn.application.integration

import br.com.devLearn.application.model.Category
import br.com.devLearn.application.model.Courses
import br.com.devLearn.application.model.User
import br.com.devLearn.application.repository.CategoryRepository
import br.com.devLearn.application.repository.CoursesRepository
import br.com.devLearn.application.repository.UserRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class CoursesRepositoryTest {
    @Autowired
    private lateinit var coursesRepository: CoursesRepository
    @Autowired
    private lateinit var userRepository: UserRepository
    @Autowired
    private lateinit var categoryRepository: CategoryRepository

    @AfterEach
    fun tearDown(){
        coursesRepository.deleteAll()
    }

    @Test
    fun `should return all courses after finding them`(){
        //given
        val authorEntity = userRepository.save(User(1, "Kenma123", "12345678", "Kenma"))
        val categoryEntity = categoryRepository.save(Category(1, "Backend"))
        coursesRepository.save(Courses(null, "Spring Boot", "Curso de Spring Boot no Kotlin", authorEntity, categoryEntity))
        coursesRepository.save(Courses(null, "Kotlin", "Curso de POO em Kotlin", authorEntity, categoryEntity))
        //when
        val result = coursesRepository.findAll()
        //then
        Assertions.assertEquals(2, result.size)
    }

    @Test
    fun `should return the course after finding it by id`(){
        //given
        val authorEntity = userRepository.save(User(1, "Kenma123", "12345678", "Kenma"))
        val categoryEntity = categoryRepository.save(Category(1, "Backend"))
        val subject = Courses(null, "Spring Boot", "Curso de Spring Boot no Kotlin", authorEntity, categoryEntity)
        val id = coursesRepository.save(subject).id ?: throw java.lang.RuntimeException("Course id is null")
        //when
        val result = coursesRepository.getById(id)
        //then
        Assertions.assertEquals(subject.id, result.id)
        Assertions.assertEquals(subject.name, result.name)
        Assertions.assertEquals(subject.description, result.description)
        Assertions.assertEquals(subject.author, result.author)
        Assertions.assertEquals(subject.category, result.category)
    }

    @Test
    fun `verify if the course is truly deleted`(){
        //given
        val authorEntity = userRepository.save(User(1, "Kenma123", "12345678", "Kenma"))
        val categoryEntity = categoryRepository.save(Category(1, "Backend"))
        val subject = Courses(null, "Spring Boot", "Curso de Spring Boot no Kotlin", authorEntity, categoryEntity)
        val id = coursesRepository.save(subject).id ?: throw java.lang.RuntimeException("Course id is null")
        //when
        coursesRepository.deleteById(id)
        //then
        Assertions.assertFalse(coursesRepository.existsById(id))
    }

    @Test
    fun `verify if it return the course after saving it`(){
        //given
        val authorEntity = userRepository.save(User(1, "Kenma123", "12345678", "Kenma"))
        val categoryEntity = categoryRepository.save(Category(1, "Backend"))
        val subject = Courses(null, "Spring Boot", "Curso de Spring Boot no Kotlin", authorEntity, categoryEntity)
        //when
        val result = coursesRepository.save(subject)
        //then
        Assertions.assertEquals(subject.id, result.id)
        Assertions.assertEquals(subject.name, result.name)
        Assertions.assertEquals(subject.description, result.description)
        Assertions.assertEquals(subject.author, result.author)
        Assertions.assertEquals(subject.category, result.category)
    }
}