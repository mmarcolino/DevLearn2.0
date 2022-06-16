package br.com.devLearn.application.integration

import br.com.devLearn.application.model.Category
import br.com.devLearn.application.model.Course
import br.com.devLearn.application.model.User
import br.com.devLearn.application.model.Video
import br.com.devLearn.application.repository.CategoryRepository
import br.com.devLearn.application.repository.CourseRepository
import br.com.devLearn.application.repository.UserRepository
import br.com.devLearn.application.repository.VideoRepository
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

    @AfterEach
    fun tearDown(){
        videoRepository.deleteAll()
    }

    @Test
    fun `Should return all videos after finding them`(){
        //given
        val authorEntity = userRepository.save(User(1, "Kenma123", "12345678", "Kenma"))
        val categoryEntity = categoryRepository.save(Category(1, "Backend"))
        val courseEntity = courseRepository.save(Course(null, "Spring Boot", "Curso de Spring Boot no Kotlin", authorEntity, categoryEntity))
        videoRepository.save(Video(null, "Introdução", "Introdução sobre spring boot", LocalDate.now(), "https://www.youtube.com/watch?v=j5Tt8bmeCBA", courseEntity))
        videoRepository.save(Video(null, "Primeiros passos", "Configurando o spring boot", LocalDate.now(), "https://www.youtube.com/watch?v=j5Tt8bmeCBA", courseEntity))
        //when
        val result = videoRepository.findAll()
        //then
        Assertions.assertEquals(2, result.size)
    }

    @Test
    fun `should return the video after finding it by id`(){
        //given
        val authorEntity = userRepository.save(User(1, "Kenma123", "12345678", "Kenma"))
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
        Assertions.assertEquals(subject.curso, result.curso)
    }

    @Test
    fun `verify if the video is truly deleted`(){
        //given
        val authorEntity = userRepository.save(User(1, "Kenma123", "12345678", "Kenma"))
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
        val authorEntity = userRepository.save(User(1, "Kenma123", "12345678", "Kenma"))
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
        Assertions.assertEquals(subject.curso, result.curso)
    }
}