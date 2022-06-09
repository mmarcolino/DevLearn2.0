package br.com.devLearn.application.component

import br.com.devLearn.application.model.Categories
import br.com.devLearn.application.model.Courses
import br.com.devLearn.application.model.Users
import br.com.devLearn.application.model.Videos
import br.com.devLearn.application.repository.CategoriesRepository
import br.com.devLearn.application.repository.CoursesRepository
import br.com.devLearn.application.repository.UsersRepository
import br.com.devLearn.application.repository.VideosRepository
import io.restassured.RestAssured
import io.restassured.http.ContentType
import org.hamcrest.CoreMatchers
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.HttpStatus
import org.springframework.test.context.ActiveProfiles
import java.sql.Date
import java.time.LocalDate

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@ActiveProfiles("test")
class VideosControllerTest {
    @LocalServerPort
    private val port: Int = 0

    @Autowired
    private lateinit var videosRepository: VideosRepository
    @Autowired
    private lateinit var usersRepository: UsersRepository
    @Autowired
    private lateinit var categoriesRepository: CategoriesRepository
    @Autowired
    private lateinit var coursesRepository: CoursesRepository

    @BeforeEach
    fun setUp(){
        RestAssured.baseURI = "http://localhost:$port/"
        RestAssured.useRelaxedHTTPSValidation()
    }

    @AfterEach
    fun tearDown(){
        videosRepository.deleteAll()
    }

    @Test
    fun `should list videos, then return ok status and response body`(){
        val authorEntity = usersRepository.save(Users(1, "Kenma123", "12345678", "Kenma"))
        val categoriesEntity = categoriesRepository.save(Categories(1, "Backend"))
        val courseEntity = coursesRepository.save(Courses(null, "Spring Boot", "Curso de Spring Boot no Kotlin", authorEntity, categoriesEntity))
        videosRepository.save(Videos(null, "Introdução", "Introdução sobre Spring Boot", LocalDate.now(), "https://www.youtube.com/watch?v=j5Tt8bmeCBA", courseEntity))
        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .get("/videos")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("[0].id", CoreMatchers.equalTo(1))
            .body("[0].name", CoreMatchers.equalTo("Introdução"))
            .body("[0].description", CoreMatchers.equalTo("Introdução sobre Spring Boot"))
            .body("[0].date", CoreMatchers.equalTo(LocalDate.now()))
            .body("[0].url", CoreMatchers.equalTo("https://www.youtube.com/watch?v=j5Tt8bmeCBA"))
            .body("[0].courseId", CoreMatchers.equalTo(1))
    }

    @Test
    fun `Should get video by id, then return ok status and response body`(){
        val authorEntity = usersRepository.save(Users(1, "Kenma123", "12345678", "Kenma"))
        val categoriesEntity = categoriesRepository.save(Categories(1, "Backend"))
        val courseEntity = coursesRepository.save(Courses(null, "Spring Boot", "Curso de Spring Boot no Kotlin", authorEntity, categoriesEntity))
        val videoId = videosRepository.save(Videos(null, "Introdução", "Introdução sobre Spring Boot", LocalDate.now(), "https://www.youtube.com/watch?v=j5Tt8bmeCBA", courseEntity)).id
        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .get("/videos/$videoId")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("id", CoreMatchers.notNullValue())
            .body("name", CoreMatchers.equalTo("Introdução"))
            .body("description", CoreMatchers.equalTo("Introdução sobre Spring Boot"))
            .body("date",  CoreMatchers.equalTo(LocalDate.now()))
            .body("url", CoreMatchers.equalTo("https://www.youtube.com/watch?v=j5Tt8bmeCBA"))
            .body("courseId", CoreMatchers.equalTo(1))
    }

    @Test
    fun `Should create a video, then return a create status and response body`(){
        val videoJson = """{"name": "Introdução", "description": "Introdução sobre Spring Boot","date": "03/03/2022", "url": "https://www.youtube.com/watch?v=j5Tt8bmeCBA", "courseId": "1"}"""
        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .body(videoJson)
            .post("/videos")
            .then()
            .statusCode(HttpStatus.CREATED.value())
            .body("id", CoreMatchers.notNullValue())
            .body("name", CoreMatchers.equalTo("Introdução"))
            .body("description", CoreMatchers.equalTo("Introdução sobre Spring Boot"))
            .body("date",  CoreMatchers.equalTo(LocalDate.now()))
            .body("url", CoreMatchers.equalTo("https://www.youtube.com/watch?v=j5Tt8bmeCBA"))
            .body("courseId", CoreMatchers.equalTo(1))
    }

    @Test
    fun `should update a video, then return ok status and response body`(){
        val authorEntity = usersRepository.save(Users(1, "Kenma123", "12345678", "Kenma"))
        val categoriesEntity = categoriesRepository.save(Categories(1, "Backend"))
        val courseEntity = coursesRepository.save(Courses(null, "Spring Boot", "Curso de Spring Boot no Kotlin", authorEntity, categoriesEntity))
        val videoId = videosRepository.save(Videos(null, "Introdução", "Introdução sobre Spring Boot", LocalDate.now(), "https://www.youtube.com/watch?v=j5Tt8bmeCBA", courseEntity)).id
        val videoJson = """{"url": "https://www.youtube.com/watch?v=wp7qctyVU2s"}"""
        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .body(videoJson)
            .put("/videos/$videoId")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("id", CoreMatchers.notNullValue())
            .body("name", CoreMatchers.equalTo("Introdução"))
            .body("description", CoreMatchers.equalTo("Introdução sobre Spring Boot"))
            .body("date",  CoreMatchers.equalTo(LocalDate.now()))
            .body("url", CoreMatchers.equalTo("https://www.youtube.com/watch?v=wp7qctyVU2s"))
            .body("courseId", CoreMatchers.equalTo(1))
    }

    @Test
    fun `should delete video, return status no content`(){
        val authorEntity = usersRepository.save(Users(1, "Kenma123", "12345678", "Kenma"))
        val categoriesEntity = categoriesRepository.save(Categories(1, "Backend"))
        val courseEntity = coursesRepository.save(Courses(null, "Spring Boot", "Curso de Spring Boot no Kotlin", authorEntity, categoriesEntity))
        val videoId = videosRepository.save(Videos(null, "Introdução", "Introdução sobre Spring Boot", LocalDate.now(), "https://www.youtube.com/watch?v=j5Tt8bmeCBA", courseEntity)).id
        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .delete("/videos/$videoId")
            .then()
            .statusCode(HttpStatus.NO_CONTENT.value())
    }
}