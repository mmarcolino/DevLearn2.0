package br.com.devLearn.application.component

import br.com.devLearn.application.model.*
import br.com.devLearn.application.repository.*
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
import java.time.LocalDate

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@ActiveProfiles("test")
class VideoControllerTest {
    @LocalServerPort
    private val port: Int = 0

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


    @BeforeEach
    fun setUp(){
        RestAssured.baseURI = "http://localhost:$port/"
        RestAssured.useRelaxedHTTPSValidation()
    }

    @AfterEach
    fun tearDown(){
        videoRepository.deleteAll()
    }

    @Test
    fun `should list videos, then return ok status and response body`(){
        val role = listOf(roleRepository.save(Role(1, "ADMIN")))
        val authorEntity = userRepository.save(User(1, "Kenma123", "12345678", "Kenma", role))
        val categoryEntity = categoryRepository.save(Category(1, "Backend"))
        val courseEntity = courseRepository.save(Course(1, "Spring Boot", "Curso de Spring Boot no Kotlin", authorEntity, categoryEntity))
        videoRepository.save(Video(null, "Introdução", "Introdução sobre Spring Boot", LocalDate.now(), "https://www.youtube.com/watch?v=j5Tt8bmeCBA", courseEntity))
        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .get("/videos")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("[0].id", CoreMatchers.equalTo(1))
            .body("[0].name", CoreMatchers.equalTo("Introdução"))
            .body("[0].description", CoreMatchers.equalTo("Introdução sobre Spring Boot"))
            .body("[0].date", CoreMatchers.equalTo("2022-06-10"))
            .body("[0].url", CoreMatchers.equalTo("https://www.youtube.com/watch?v=j5Tt8bmeCBA"))
            .body("[0].cursoId", CoreMatchers.equalTo(1))
    }

    @Test
    fun `Should get video by id, then return ok status and response body`(){
        val role = listOf(roleRepository.save(Role(1, "ADMIN")))
        val authorEntity = userRepository.save(User(1, "Kenma123", "12345678", "Kenma", role))
        val categoryEntity = categoryRepository.save(Category(1, "Backend"))
        val courseEntity = courseRepository.save(Course(1, "Spring Boot", "Curso de Spring Boot no Kotlin", authorEntity, categoryEntity))
        val videoId = videoRepository.save(Video(null, "Introdução", "Introdução sobre Spring Boot", LocalDate.now(), "https://www.youtube.com/watch?v=j5Tt8bmeCBA", courseEntity)).id
        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .get("/videos/$videoId")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("id", CoreMatchers.notNullValue())
            .body("name", CoreMatchers.equalTo("Introdução"))
            .body("description", CoreMatchers.equalTo("Introdução sobre Spring Boot"))
            .body("date",  CoreMatchers.equalTo("2022-06-10"))
            .body("url", CoreMatchers.equalTo("https://www.youtube.com/watch?v=j5Tt8bmeCBA"))
            .body("cursoId", CoreMatchers.equalTo(1))
    }

    @Test
    fun `Should create a video, then return a create status and response body`(){
        val role = listOf(roleRepository.save(Role(1, "ADMIN")))
        val authorEntity = userRepository.save(User(1, "Kenma123", "12345678", "Kenma", role))
        val categoryEntity = categoryRepository.save(Category(1, "Backend"))
        courseRepository.save(Course(1, "Spring Boot", "Curso de Spring Boot no Kotlin", authorEntity, categoryEntity))
        val videoJson = """{"name": "Introdução", "description": "Introdução sobre Spring Boot","date": "2022-06-10", "url": "https://www.youtube.com/watch?v=j5Tt8bmeCBA", "cursoId": "1"}"""
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
            .body("date",  CoreMatchers.equalTo("2022-06-10"))
            .body("url", CoreMatchers.equalTo("https://www.youtube.com/watch?v=j5Tt8bmeCBA"))
            .body("cursoId", CoreMatchers.equalTo(1))
    }

    @Test
    fun `should update a video, then return ok status and response body`(){
        val role = listOf(roleRepository.save(Role(1, "ADMIN")))
        val authorEntity = userRepository.save(User(1, "Kenma123", "12345678", "Kenma", role))
        val categoryEntity = categoryRepository.save(Category(1, "Backend"))
        val courseEntity = courseRepository.save(Course(1, "Spring Boot", "Curso de Spring Boot no Kotlin", authorEntity, categoryEntity))
        val videoId = videoRepository.save(Video(null, "Introdução", "Introdução sobre Spring Boot", LocalDate.now(), "https://www.youtube.com/watch?v=j5Tt8bmeCBA", courseEntity)).id
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
            .body("date",  CoreMatchers.equalTo("2022-06-10"))
            .body("url", CoreMatchers.equalTo("https://www.youtube.com/watch?v=wp7qctyVU2s"))
            .body("cursoId", CoreMatchers.equalTo(1))
    }

    @Test
    fun `should delete video, return status no content`(){
        val role = listOf(roleRepository.save(Role(1, "ADMIN")))
        val authorEntity = userRepository.save(User(1, "Kenma123", "12345678", "Kenma", role))
        val categoryEntity = categoryRepository.save(Category(1, "Backend"))
        val courseEntity = courseRepository.save(Course(1, "Spring Boot", "Curso de Spring Boot no Kotlin", authorEntity, categoryEntity))
        val videoId = videoRepository.save(Video(null, "Introdução", "Introdução sobre Spring Boot", LocalDate.now(), "https://www.youtube.com/watch?v=j5Tt8bmeCBA", courseEntity)).id
        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .delete("/videos/$videoId")
            .then()
            .statusCode(HttpStatus.NO_CONTENT.value())
    }
}