package br.com.devLearn.application.integration

import br.com.devLearn.application.model.*
import br.com.devLearn.application.repository.*
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.time.LocalDate

@DataJpaTest
class VideoRepositoryTest {

    @Autowired
    private lateinit var videoRepository: VideoRepository
    @Autowired
    private lateinit var userRepository: UserRepository
    @Autowired
    private lateinit var categoryRepository: CategoryRepository
    @Autowired
    private lateinit var courseRepository: CourseRepository
    @Autowired
    private lateinit var roleRepository: RoleRepository

    @AfterEach
    fun tearDown(){
        videoRepository.deleteAll()
    }

    @Test
    fun `Should return all videos after finding them`(){
        //given
        val role = listOf(roleRepository.save(Role(1, "ADMIN")))
        val authorEntity = userRepository.save(User(1, "Kenma123", "12345678", "Kenma", role))
        val categoryEntity = categoryRepository.save(Category(1, "Backend"))
        val courseEntity = courseRepository.save(Course(null, "Spring Boot", "Curso de Spring Boot no Kotlin", authorEntity, categoryEntity))
        val subject = videoRepository.save(Video(0, "Introdução", "Introdução sobre spring boot", LocalDate.now(), "https://www.youtube.com/watch?v=j5Tt8bmeCBA", courseEntity))
        val subject2 = videoRepository.save(Video(1, "Primeiros passos", "Configurando o spring boot", LocalDate.now(), "https://www.youtube.com/watch?v=j5Tt8bmeCBA", courseEntity))
        //when
        val result = videoRepository.findAll()
        //then
        Assertions.assertEquals(2, result.size)
        Assertions.assertEquals(subject.id, result[0].id)
        Assertions.assertEquals(subject.name, result[0].name)
        Assertions.assertEquals(subject.course, result[0].course)
        Assertions.assertEquals(subject.url, result[0].url)
        Assertions.assertEquals(subject.date, result[0].date)
        Assertions.assertEquals(subject.description, result[0].description)
        Assertions.assertEquals(subject2.id, result[1].id)
        Assertions.assertEquals(subject2.name, result[1].name)
        Assertions.assertEquals(subject2.course, result[1].course)
        Assertions.assertEquals(subject2.url, result[1].url)
        Assertions.assertEquals(subject2.date, result[1].date)
        Assertions.assertEquals(subject2.description, result[1].description)
    }

    @Test
    fun `should return the video after finding it by id`(){
        //given
        val role = listOf(roleRepository.save(Role(1, "ADMIN")))
        val authorEntity = userRepository.save(User(1, "Kenma123", "12345678", "Kenma", role))
        val categoryEntity = categoryRepository.save(Category(1, "Backend"))
        val courseEntity = courseRepository.save(Course(null, "Spring Boot", "Curso de Spring Boot no Kotlin", authorEntity, categoryEntity))
        val subject = Video(null, "Introdução", "Introdução sobre spring boot", LocalDate.now(), "https://www.youtube.com/watch?v=j5Tt8bmeCBA", courseEntity)
        val id = videoRepository.save(subject).id ?: throw java.lang.RuntimeException("Course id is null")
        //when
        val result = videoRepository.getById(id)
        //then
        Assertions.assertEquals(subject.id, result.id)
        Assertions.assertEquals(subject.name, result.name)
        Assertions.assertEquals(subject.description, result.description)
        Assertions.assertEquals(subject.date, result.date)
        Assertions.assertEquals(subject.url, result.url)
        Assertions.assertEquals(subject.course, result.course)
    }

    @Test
    fun `verify if the video is truly deleted`(){
        //given
        val role = listOf(roleRepository.save(Role(1, "ADMIN")))
        val authorEntity = userRepository.save(User(1, "Kenma123", "12345678", "Kenma", role))
        val categoryEntity = categoryRepository.save(Category(1, "Backend"))
        val courseEntity = courseRepository.save(Course(null, "Spring Boot", "Curso de Spring Boot no Kotlin", authorEntity, categoryEntity))
        val subject = Video(null, "Introdução", "Introdução sobre spring boot", LocalDate.now(), "https://www.youtube.com/watch?v=j5Tt8bmeCBA", courseEntity)
        val id = videoRepository.save(subject).id ?: throw java.lang.RuntimeException("Course id is null")
        //when
        videoRepository.deleteById(id)
        //then
        Assertions.assertFalse(videoRepository.existsById(id))
    }

    @Test
    fun `verify if it return the video after saving it`(){
        //given
        val role = listOf(roleRepository.save(Role(1, "ADMIN")))
        val authorEntity = userRepository.save(User(1, "Kenma123", "12345678", "Kenma", role))
        val categoryEntity = categoryRepository.save(Category(1, "Backend"))
        val courseEntity = courseRepository.save(Course(null, "Spring Boot", "Curso de Spring Boot no Kotlin", authorEntity, categoryEntity))
        val subject = Video(null, "Introdução", "Introdução sobre spring boot", LocalDate.now(), "https://www.youtube.com/watch?v=j5Tt8bmeCBA", courseEntity)
        //when
        val result = videoRepository.save(subject)
        //then
        Assertions.assertEquals(subject.id, result.id)
        Assertions.assertEquals(subject.name, result.name)
        Assertions.assertEquals(subject.description, result.description)
        Assertions.assertEquals(subject.date, result.date)
        Assertions.assertEquals(subject.url, result.url)
        Assertions.assertEquals(subject.course, result.course)
    }
}