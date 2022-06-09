package br.com.devLearn.application.integration

import br.com.devLearn.application.model.Categories
import br.com.devLearn.application.model.Courses
import br.com.devLearn.application.model.Users
import br.com.devLearn.application.model.Videos
import br.com.devLearn.application.repository.CategoriesRepository
import br.com.devLearn.application.repository.CoursesRepository
import br.com.devLearn.application.repository.UsersRepository
import br.com.devLearn.application.repository.VideosRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import java.sql.Date
import java.time.LocalDate

@DataJpaTest
class VideosRepositoryTest {

    @Autowired
    private lateinit var videosRepository: VideosRepository
    @Autowired
    private lateinit var usersRepository: UsersRepository
    @Autowired
    private lateinit var categoriesRepository: CategoriesRepository
    @Autowired
    private lateinit var coursesRepository: CoursesRepository

    @AfterEach
    fun tearDown(){
        videosRepository.deleteAll()
    }

    @Test
    fun `Should return all videos after finding them`(){
        //given
        val authorEntity = usersRepository.save(Users(1, "Kenma123", "12345678", "Kenma"))
        val categoriesEntity = categoriesRepository.save(Categories(1, "Backend"))
        val courseEntity = coursesRepository.save(Courses(null, "Spring Boot", "Curso de Spring Boot no Kotlin", authorEntity, categoriesEntity))
        videosRepository.save(Videos(null, "Introdução", "Introdução sobre spring boot", LocalDate.now(), "https://www.youtube.com/watch?v=j5Tt8bmeCBA", courseEntity))
        videosRepository.save(Videos(null, "Primeiros passos", "Configurando o spring boot", LocalDate.now(), "https://www.youtube.com/watch?v=j5Tt8bmeCBA", courseEntity))
        //when
        val result = videosRepository.findAll()
        //then
        Assertions.assertEquals(2, result.size)
    }

    @Test
    fun `should return the video after finding it by id`(){
        //given
        val authorEntity = usersRepository.save(Users(1, "Kenma123", "12345678", "Kenma"))
        val categoriesEntity = categoriesRepository.save(Categories(1, "Backend"))
        val courseEntity = coursesRepository.save(Courses(null, "Spring Boot", "Curso de Spring Boot no Kotlin", authorEntity, categoriesEntity))
        val subject = Videos(null, "Introdução", "Introdução sobre spring boot", LocalDate.now(), "https://www.youtube.com/watch?v=j5Tt8bmeCBA", courseEntity)
        val id = videosRepository.save(subject).id ?: throw java.lang.RuntimeException("Course id is null")
        //when
        val result = videosRepository.getById(id)
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
        val authorEntity = usersRepository.save(Users(1, "Kenma123", "12345678", "Kenma"))
        val categoriesEntity = categoriesRepository.save(Categories(1, "Backend"))
        val courseEntity = coursesRepository.save(Courses(null, "Spring Boot", "Curso de Spring Boot no Kotlin", authorEntity, categoriesEntity))
        val subject = Videos(null, "Introdução", "Introdução sobre spring boot", LocalDate.now(), "https://www.youtube.com/watch?v=j5Tt8bmeCBA", courseEntity)
        val id = videosRepository.save(subject).id ?: throw java.lang.RuntimeException("Course id is null")
        //when
        videosRepository.deleteById(id)
        //then
        Assertions.assertFalse(videosRepository.existsById(id))
    }

    @Test
    fun `verify if it return the video after saving it`(){
        //given
        val authorEntity = usersRepository.save(Users(1, "Kenma123", "12345678", "Kenma"))
        val categoriesEntity = categoriesRepository.save(Categories(1, "Backend"))
        val courseEntity = coursesRepository.save(Courses(null, "Spring Boot", "Curso de Spring Boot no Kotlin", authorEntity, categoriesEntity))
        val subject = Videos(null, "Introdução", "Introdução sobre spring boot", LocalDate.now(), "https://www.youtube.com/watch?v=j5Tt8bmeCBA", courseEntity)
        //when
        val result = videosRepository.save(subject)
        //then
        Assertions.assertEquals(subject.id, result.id)
        Assertions.assertEquals(subject.name, result.name)
        Assertions.assertEquals(subject.description, result.description)
        Assertions.assertEquals(subject.date, result.date)
        Assertions.assertEquals(subject.url, result.url)
        Assertions.assertEquals(subject.curso, result.curso)
    }
}