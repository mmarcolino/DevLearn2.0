package br.com.devLearn.application.component

import br.com.devLearn.application.model.Category
import br.com.devLearn.application.model.Course
import br.com.devLearn.application.model.User
import br.com.devLearn.application.repository.CategoryRepository
import br.com.devLearn.application.repository.CourseRepository
import br.com.devLearn.application.repository.UserRepository
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

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@ActiveProfiles("test")
class CourseControllerTest {
    @LocalServerPort //descobre a porta aleatória
    private var port: Int = 0

    @Autowired
    private lateinit var courseRepository: CourseRepository
    @Autowired
    private lateinit var userRepository: UserRepository
    @Autowired
    private lateinit var categoryRepository: CategoryRepository


            @BeforeEach
    fun setUp(){
        RestAssured.baseURI = "http://localhost:$port/"
        RestAssured.useRelaxedHTTPSValidation()
    }

    @AfterEach
    fun tearDown(){
        courseRepository.deleteAll()
    }

    @Test
    fun `should list courses, then return ok status and response body`(){
        val authorEntity = userRepository.save(User(1, "Kenma123", "12345678", "Kenma"))
        val categoryEntity = categoryRepository.save(Category(1, "Backend"))
        courseRepository.save(Course(1, "Spring Boot", "Curso de Spring Boot no Kotlin", authorEntity, categoryEntity))
        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .get("/courses")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("[0].id", CoreMatchers.notNullValue())
            .body("[0].name", CoreMatchers.equalTo("Spring Boot"))
            .body("[0].description", CoreMatchers.equalTo("Curso de Spring Boot no Kotlin"))
            .body("[0].authorId", CoreMatchers.equalTo(1))
            .body("[0].categoryId", CoreMatchers.equalTo(1))
    }

    @Test
    fun `should get course by id, then return ok status and response body `(){
        val authorEntity = userRepository.save(User(1, "Kenma123", "12345678", "Kenma"))
        val categoryEntity = categoryRepository.save(Category(1, "Backend"))
        val courseId = courseRepository.save(Course(1, "Spring Boot", "Curso de Spring Boot no Kotlin", authorEntity, categoryEntity)).id
        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .get("/courses/$courseId")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("id", CoreMatchers.notNullValue())
            .body("name", CoreMatchers.equalTo("Spring Boot"))
            .body("description", CoreMatchers.equalTo("Curso de Spring Boot no Kotlin"))
            .body("authorId", CoreMatchers.equalTo(1))
            .body("categoryId", CoreMatchers.equalTo(1))
    }

    @Test
    fun `should create course, then return create status and response body`(){
        val courseJson = """{"name":"Spring Boot", "description":"Curso de Spring Boot no Kotlin", "authorId": "1", "categoryId": "1"}"""
        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .body(courseJson)
            .post("/courses")
            .then()
            .statusCode(HttpStatus.CREATED.value())
            .body("id", CoreMatchers.notNullValue())
            .body("name", CoreMatchers.equalTo("Spring Boot"))
            .body("description", CoreMatchers.equalTo("Curso de Spring Boot no Kotlin"))
            .body("authorId", CoreMatchers.equalTo(1))
            .body("categoryId", CoreMatchers.equalTo(1))
    }

    @Test
    fun `Should update course, then return ok status and response body`(){
        val authorEntity = userRepository.save(User(1, "Kenma123", "12345678", "Kenma"))
        val categoryEntity = categoryRepository.save(Category(1, "Backend"))
        val courseId = courseRepository.save(Course(1, "Spring Boot", "Curso de Spring Boot no Kotlin", authorEntity, categoryEntity)).id
        val courseJson = """{"name":"Teste"}"""
        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .body(courseJson)
            .put("/courses/$courseId")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("id", CoreMatchers.notNullValue())
            .body("name", CoreMatchers.equalTo("Teste"))
            .body("description", CoreMatchers.equalTo("Curso de Spring Boot no Kotlin"))
            .body("authorId", CoreMatchers.equalTo(1))
            .body("categoryId", CoreMatchers.equalTo(1))
    }
    @Test
    fun `Should delete course, returm status no content`(){
        val authorEntity = userRepository.save(User(1, "Kenma123", "12345678", "Kenma"))
        val categoryEntity = categoryRepository.save(Category(1, "Backend"))
        val courseId = courseRepository.save(Course(1, "Spring Boot", "Curso de Spring Boot no Kotlin", authorEntity, categoryEntity)).id
        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .delete("/courses/$courseId")
            .then()
            .statusCode(HttpStatus.NO_CONTENT.value())
    }
}