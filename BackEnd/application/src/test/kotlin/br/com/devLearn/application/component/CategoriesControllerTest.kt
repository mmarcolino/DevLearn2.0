package br.com.devLearn.application.component

import br.com.devLearn.application.model.Categories
import br.com.devLearn.application.repository.CategoriesRepository
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
import kotlin.random.Random

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase
@ActiveProfiles("test")
class CategoriesControllerTest {
    @LocalServerPort
    private val port: Int = 0

    @Autowired
    private lateinit var categoriesRepository: CategoriesRepository

    @BeforeEach
    fun setUp(){
        RestAssured.baseURI = "http://localhost:$port/"
        RestAssured.useRelaxedHTTPSValidation()
    }
    @AfterEach
    fun tearDown(){
        categoriesRepository.deleteAll()
    }

    @Test
    fun `should list categories, then return ok status and response body`(){
        categoriesRepository.save(Categories(1, "Backend"))
        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .get("/categories")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("[0].id", CoreMatchers.notNullValue())
            .body("[0].name", CoreMatchers.equalTo("Backend"))

    }

    @Test
    fun `should get categorie by id, then return ok status and response body`(){
        val categoryId = categoriesRepository.save(Categories(1, "Backend")).id
        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .get("/categories/$categoryId")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("id", CoreMatchers.notNullValue())
            .body("name", CoreMatchers.equalTo("Backend"))
    }

    @Test
    fun `Should create a category, then return create status and response body`(){
        val categoryJson = """{"name": "Backend"}"""
        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .body(categoryJson)
            .post("/categories")
            .then()
            .statusCode(HttpStatus.CREATED.value())
            .body("id", CoreMatchers.notNullValue())
            .body("name", CoreMatchers.equalTo("Backend"))
    }

    @Test
    fun `Should update category, then return ok status and response body`(){
        val categoryId = categoriesRepository.save(Categories(1, "Backend")).id
        val categoryJson = """{"name": "Backend"}"""
        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .body(categoryJson)
            .put("/categories/$categoryId")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("id", CoreMatchers.notNullValue())
            .body("name", CoreMatchers.equalTo("Backend"))
    }
    @Test
    fun `Should delete category, return status no content`(){
        val categoryId = categoriesRepository.save(Categories(1, "Backend")).id
        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .delete("/categories/$categoryId")
            .then()
            .statusCode(HttpStatus.NO_CONTENT.value())
    }
}