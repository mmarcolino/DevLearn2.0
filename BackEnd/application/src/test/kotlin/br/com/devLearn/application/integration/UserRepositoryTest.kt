package br.com.devLearn.application.integration

import br.com.devLearn.application.model.Role
import br.com.devLearn.application.model.User
import br.com.devLearn.application.repository.RoleRepository
import br.com.devLearn.application.repository.UserRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest


@DataJpaTest
class UserRepositoryTest {
    @Autowired
    private lateinit var userRepository: UserRepository
    @Autowired
    private lateinit var roleRepository: RoleRepository

    @AfterEach
    fun tearDown(){
        userRepository.deleteAll()
    }

    @Test
    fun `return all users after finding them`(){
        userRepository.deleteAll()

        //given
        val role = listOf(roleRepository.save(Role(1, "ADMIN")))
        val subject = userRepository.save(User(null, "Kenma123", "12345678", "Kenma", role))
        val subject2 = userRepository.save(User(null, "Johan123", "12345678", "Johan", role))

        //when
        val result = userRepository.findAll()

        //then
        Assertions.assertEquals(2, result.size)
        Assertions.assertEquals(subject.id, result[0].id)
        Assertions.assertEquals(subject.username, result[0].username)
        Assertions.assertEquals(subject.password, result[0].password)
        Assertions.assertEquals(subject.name, result[0].name)
        Assertions.assertEquals(subject2.id, result[1].id)
        Assertions.assertEquals(subject2.username, result[1].username)
        Assertions.assertEquals(subject2.password, result[1].password)
        Assertions.assertEquals(subject2.name, result[1].name)
    }

    @Test
    fun `return user after finding it by id`(){
        //given
        val role = listOf(roleRepository.save(Role(1, "ADMIN")))
        val user =User(null, "Kenma123", "12345678", "Kenma", role)
        val userId = userRepository.save(user).id ?: throw java.lang.RuntimeException("User id is null")
        //when
        val result = userRepository.getById(userId)
        //then
        Assertions.assertEquals(userId, result.id)
        Assertions.assertEquals("Kenma123", result.username)
        Assertions.assertEquals("12345678", result.password)
        Assertions.assertEquals("Kenma", result.name)
    }

    @Test
    fun `verify if user is truly deleted`(){
        //given
        val role = listOf(roleRepository.save(Role(1, "ADMIN")))
        val user = User(null, "Kenma123", "12345678", "Kenma", role)
        val userId = userRepository.save(user).id ?: throw java.lang.RuntimeException("User id is null")
        //when
        userRepository.deleteById(userId)
        //then
        Assertions.assertFalse(userRepository.existsById(userId))
    }

    @Test
    fun `verify if it returns the user after saving it`(){
        //given
        val role = listOf(roleRepository.save(Role(1, "ADMIN")))
        val user = User(null, "Kenma123", "12345678", "Kenma", role)
        //when
        val result = userRepository.save(user)
        //then
        Assertions.assertEquals(user.id, result.id)
        Assertions.assertEquals(user.username, result.username)
        Assertions.assertEquals(user.password, result.password)
        Assertions.assertEquals(user.name, result.name)
    }

}