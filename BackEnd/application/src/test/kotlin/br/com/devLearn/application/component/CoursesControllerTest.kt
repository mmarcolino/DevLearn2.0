package br.com.devLearn.application.component

import br.com.devLearn.application.model.Categories
import br.com.devLearn.application.model.Courses
import br.com.devLearn.application.model.Users
import br.com.devLearn.application.repository.CategoriesRepository
import br.com.devLearn.application.repository.CoursesRepository
import br.com.devLearn.application.repository.UsersRepository
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
class CoursesControllerTest {
    @LocalServerPort //descobre a porta aleatória
    private var port: Int = 0

    @Autowired
    private lateinit var coursesRepository: CoursesRepository
    @Autowired
    private lateinit var usersRepository: UsersRepository
    @Autowired
    private lateinit var categoriesRepository: CategoriesRepository


            @BeforeEach
    fun setUp(){
        RestAssured.baseURI = "http://localhost:$port/"
        RestAssured.useRelaxedHTTPSValidation()
    }

    @AfterEach
    fun tearDown(){
        coursesRepository.deleteAll()
    }

    @Test
    fun `should list courses, then return ok status and response body`(){
        val authorEntity = usersRepository.save(Users(1, "Kenma123", "12345678", "Kenma"))
        val categoriesEntity = categoriesRepository.save(Categories(1, "Backend"))
        coursesRepository.save(Courses(1, "Spring Boot", "Curso de Spring Boot no Kotlin", authorEntity, categoriesEntity))
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
        val authorEntity = usersRepository.save(Users(1, "Kenma123", "12345678", "Kenma"))
        val categoriesEntity = categoriesRepository.save(Categories(1, "Backend"))
        val courseId = coursesRepository.save(Courses(1, "Spring Boot", "Curso de Spring Boot no Kotlin", authorEntity, categoriesEntity)).id
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
        val authorEntity = usersRepository.save(Users(1, "Kenma123", "12345678", "Kenma"))
        val categoriesEntity = categoriesRepository.save(Categories(1, "Backend"))
        val courseId = coursesRepository.save(Courses(1, "Spring Boot", "Curso de Spring Boot no Kotlin", authorEntity, categoriesEntity)).id
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
        val authorEntity = usersRepository.save(Users(1, "Kenma123", "12345678", "Kenma"))
        val categoriesEntity = categoriesRepository.save(Categories(1, "Backend"))
        val courseId = coursesRepository.save(Courses(1, "Spring Boot", "Curso de Spring Boot no Kotlin", authorEntity, categoriesEntity)).id
        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .delete("/courses/$courseId")
            .then()
            .statusCode(HttpStatus.NO_CONTENT.value())
    }
}