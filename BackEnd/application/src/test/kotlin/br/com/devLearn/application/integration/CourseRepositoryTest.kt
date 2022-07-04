package br.com.devLearn.application.integration

import br.com.devLearn.application.model.Category
import br.com.devLearn.application.model.Course
import br.com.devLearn.application.model.User
import br.com.devLearn.application.repository.CategoryRepository
import br.com.devLearn.application.repository.CourseRepository
import br.com.devLearn.application.repository.UserRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class CourseRepositoryTest {
    @Autowired
    private lateinit var courseRepository: CourseRepository
    @Autowired
    private lateinit var userRepository: UserRepository
    @Autowired
    private lateinit var categoryRepository: CategoryRepository

    @AfterEach
    fun tearDown(){
        courseRepository.deleteAll()
    }

    @Test
    fun `should return all courses after finding them`(){
        //given
        val authorEntity = userRepository.save(User(1, "Kenma123", "12345678", "Kenma"))
        val categoryEntity = categoryRepository.save(Category(1, "Backend"))
        val subject = courseRepository.save(Course(null, "Spring Boot", "Curso de Spring Boot no Kotlin", authorEntity, categoryEntity))
        val subject2 = courseRepository.save(Course(null, "Kotlin", "Curso de POO em Kotlin", authorEntity, categoryEntity))
        //when
        val result = courseRepository.findAll()
        //then
        Assertions.assertEquals(2, result.size)
        Assertions.assertEquals(subject.id, result[0].id)
        Assertions.assertEquals(subject.name, result[0].name)
        Assertions.assertEquals(subject.description, result[0].description)
        Assertions.assertEquals(subject.author, result[0].author)
        Assertions.assertEquals(subject.category, result[0].category)
        Assertions.assertEquals(subject2.id, result[1].id)
        Assertions.assertEquals(subject2.name, result[1].name)
        Assertions.assertEquals(subject2.description, result[1].description)
        Assertions.assertEquals(subject2.author, result[1].author)
        Assertions.assertEquals(subject2.category, result[1].category)
    }

    @Test
    fun `should return the course after finding it by id`(){
        //given
        val authorEntity = userRepository.save(User(1, "Kenma123", "12345678", "Kenma"))
        val categoryEntity = categoryRepository.save(Category(1, "Backend"))
        val subject = Course(null, "Spring Boot", "Curso de Spring Boot no Kotlin", authorEntity, categoryEntity)
        val id = courseRepository.save(subject).id ?: throw java.lang.RuntimeException("Course id is null")
        //when
        val result = courseRepository.getById(id)
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
        val subject = Course(null, "Spring Boot", "Curso de Spring Boot no Kotlin", authorEntity, categoryEntity)
        val id = courseRepository.save(subject).id ?: throw java.lang.RuntimeException("Course id is null")
        //when
        courseRepository.deleteById(id)
        //then
        Assertions.assertFalse(courseRepository.existsById(id))
    }

    @Test
    fun `verify if it return the course after saving it`(){
        //given
        val authorEntity = userRepository.save(User(1, "Kenma123", "12345678", "Kenma"))
        val categoryEntity = categoryRepository.save(Category(1, "Backend"))
        val subject = Course(null, "Spring Boot", "Curso de Spring Boot no Kotlin", authorEntity, categoryEntity)
        //when
        val result = courseRepository.save(subject)
        //then
        Assertions.assertEquals(subject.id, result.id)
        Assertions.assertEquals(subject.name, result.name)
        Assertions.assertEquals(subject.description, result.description)
        Assertions.assertEquals(subject.author, result.author)
        Assertions.assertEquals(subject.category, result.category)
    }
}