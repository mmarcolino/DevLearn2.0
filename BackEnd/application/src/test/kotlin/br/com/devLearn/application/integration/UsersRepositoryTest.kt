package br.com.devLearn.application.integration

import br.com.devLearn.application.model.Users
import br.com.devLearn.application.repository.UsersRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest


@DataJpaTest
class UsersRepositoryTest {
    @Autowired
    private lateinit var usersRepository: UsersRepository

    @AfterEach
    fun tearDown(){
        usersRepository.deleteAll()
    }

    @Test
    fun `return all users after finding them`(){
        usersRepository.deleteAll()

        //given
        usersRepository.save(Users(null, "Kenma123", "12345678", "Kenma"))
        usersRepository.save(Users(null, "Johan123", "12345678", "Johan"))

        //when
        val result = usersRepository.findAll()

        //then
        Assertions.assertEquals(2, result.size)
    }

    @Test
    fun `return user after finding it by id`(){
        //given
        val users =Users(null, "Kenma123", "12345678", "Kenma")
        val userId = usersRepository.save(users).id ?: throw java.lang.RuntimeException("User id is null")
        //when
        val result = usersRepository.getById(userId)
        //then
        Assertions.assertEquals(userId, result.id)
        Assertions.assertEquals("Kenma123", result.username)
        Assertions.assertEquals("12345678", result.password)
        Assertions.assertEquals("Kenma", result.name)
    }

    @Test
    fun `verify if user is truly deleted`(){
        //given
        val users = Users(null, "Kenma123", "12345678", "Kenma")
        val userId = usersRepository.save(users).id ?: throw java.lang.RuntimeException("User id is null")
        //when
        usersRepository.deleteById(userId)
        //then
        Assertions.assertFalse(usersRepository.existsById(userId))
    }

    @Test
    fun `verify if it returns the user after saving it`(){
        //given
        val users = Users(null, "Kenma123", "12345678", "Kenma")
        //when
        val result = usersRepository.save(users)
        //then
        Assertions.assertEquals(users.id, result.id)
        Assertions.assertEquals(users.username, result.username)
        Assertions.assertEquals(users.password, result.password)
        Assertions.assertEquals(users.name, result.name)
    }

}