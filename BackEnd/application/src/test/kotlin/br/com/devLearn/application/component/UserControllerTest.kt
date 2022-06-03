package br.com.devLearn.application.component

import br.com.devLearn.application.model.User
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
class UserControllerTest {
   @LocalServerPort //descobre a porta aleatória
    private var port: Int = 0

    @Autowired
    private lateinit var userRepository: UserRepository

    @BeforeEach
    fun setUp(){
        RestAssured.baseURI = "http://localhost:$port/"
        RestAssured.useRelaxedHTTPSValidation()
    }

    @AfterEach
    fun tearDown(){
        userRepository.deleteAll()
    }

    @Test
    fun `should list users then, return ok status and response body`(){
        userRepository.save(User(1, "Kenma123", "12345678", "Kenma"))
        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .get("/user")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("[0].id", CoreMatchers.notNullValue())
            .body("[0].username", CoreMatchers.equalTo("Kenma123"))
            .body("[0].password", CoreMatchers.equalTo("12345678"))
            .body("[0].name", CoreMatchers.equalTo("Kenma"))
    }
    @Test
    fun `should get user by id, then return ok status and response body`(){
        val userId = userRepository.save(User(1, "Kenma123", "12345678", "Kenma")).id
        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .get("/user/$userId")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("id", CoreMatchers.notNullValue())
            .body("username", CoreMatchers.equalTo("Kenma123"))
            .body("password", CoreMatchers.equalTo("12345678"))
            .body("name", CoreMatchers.equalTo("Kenma"))
    }
    @Test
    fun `Should create user, then return create status and response body`(){
        val userJson = """{"username": "Kenma123", "password": "12345678", "name": "Kenma"}"""
        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .body(userJson)
            .post("/user")
            .then()
            .statusCode(HttpStatus.CREATED.value())
            .body("id", CoreMatchers.notNullValue())
            .body("username", CoreMatchers.equalTo("Kenma123"))
            .body("password", CoreMatchers.equalTo("12345678"))
            .body("name", CoreMatchers.equalTo("Kenma"))
    }
    @Test
    fun `Should update user, then return ok status and response body`(){
        val userId = userRepository.save(User(1, "Kenma123", "12345678", "Kenma")).id
        val userJson = """{"username":"mmarcolino"}"""
        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .body(userJson)
            .put("/user/$userId")
            .then()
            .statusCode(HttpStatus.OK.value())
            .body("id", CoreMatchers.notNullValue())
            .body("username", CoreMatchers.equalTo("mmarcolino"))
            .body("password", CoreMatchers.equalTo("12345678"))
            .body("name", CoreMatchers.equalTo("Kenma"))
    }
    @Test
    fun `Should delete user, return status no content`(){
        val userId = userRepository.save(User(1, "Kenma123", "12345678", "Kenma")).id
        RestAssured
            .given()
            .contentType(ContentType.JSON)
            .delete("/user/$userId")
            .then()
            .statusCode(HttpStatus.NO_CONTENT.value())
    }
}