package br.com.devLearn.application.unit

import br.com.devLearn.application.model.User
import br.com.devLearn.application.repository.UserRepository
import br.com.devLearn.application.service.UserService
import io.mockk.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.random.Random


class UserServiceTest {

    private val userRepository: UserRepository = mockk(relaxed = true)
    private val userService = UserService(userRepository)
    private val mockedUser = User(Random.nextLong(0, Long.MAX_VALUE), "mmarcolino", "12345678", "matheus")
    private val userId = this.mockedUser.id

    @Test
    fun `it should return all registred users`(){
        //given
        every { userRepository.findAll() } returns emptyList()
        //when
        val result = userService.listUsers()
        //then
        verify (exactly = 1){ userRepository.findAll() }
    }
    @Test
    fun `it should return the registred user by id`(){
        //given
        every { userRepository.findById(userId!!) } returns Optional.of(mockedUser)
        //when
        val result = userService.getUserById(userId!!)
        //then
        Assertions.assertEquals(mockedUser.id, result.id)
        Assertions.assertEquals(mockedUser.username, result.username)
        Assertions.assertEquals(mockedUser.password, result.password)
        Assertions.assertEquals(mockedUser.name, result.name)
        verify(exactly = 1) { userRepository.findById(userId) }
    }
    @Test
    fun `it should store and return a user from the database`(){
        //given
        val newUser = User(
            id = mockedUser.id,
            username = mockedUser.username,
            password = mockedUser.password,
            name = mockedUser.name
        )
        every { userRepository.save(any()) } returns mockedUser
        //when
        val result = userService.storeUser(newUser)
        //then
        Assertions.assertEquals(mockedUser.id, result.id)
        Assertions.assertEquals(mockedUser.username, result.username)
        Assertions.assertEquals(mockedUser.password, result.password)
        Assertions.assertEquals(mockedUser.name, result.name)
        verify(exactly = 1) { userRepository.save(any()) }
    }
    @Test
    fun `it should delete a user from the database`(){
        //given
        every { userRepository.deleteById(userId!!) } just Runs

        //when
        userService.deleteUser(userId!!)

        //then
        verify (exactly = 1){ userRepository.deleteById(userId) }
    }
}