package br.com.devLearn.application.unit

import br.com.devLearn.application.model.Users
import br.com.devLearn.application.repository.UsersRepository
import br.com.devLearn.application.service.UsersService
import io.mockk.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.random.Random


class UsersServiceTest {

    private val usersRepository: UsersRepository = mockk(relaxed = true)
    private val usersService = UsersService(usersRepository)
    private val mockedUsers = Users(Random.nextLong(0, Long.MAX_VALUE), "mmarcolino", "12345678", "matheus")
    private val userId = this.mockedUsers.id

    @Test
    fun `it should return all registred users`(){
        //given
        every { usersRepository.findAll() } returns emptyList()
        //when
        val result = usersService.listUsers()
        //then
        verify (exactly = 1){ usersRepository.findAll() }
    }
    @Test
    fun `it should return the registred user by id`(){
        //given
        every { usersRepository.findById(userId!!) } returns Optional.of(mockedUsers)
        //when
        val result = usersService.getUserById(userId!!)
        //then
        Assertions.assertEquals(mockedUsers.id, result.id)
        Assertions.assertEquals(mockedUsers.username, result.username)
        Assertions.assertEquals(mockedUsers.password, result.password)
        Assertions.assertEquals(mockedUsers.name, result.name)
        verify(exactly = 1) { usersRepository.findById(userId) }
    }
    @Test
    fun `it should store and return a user from the database`(){
        //given
        val newUsers = Users(
            id = mockedUsers.id,
            username = mockedUsers.username,
            password = mockedUsers.password,
            name = mockedUsers.name
        )
        every { usersRepository.save(any()) } returns mockedUsers
        //when
        val result = usersService.storeUser(newUsers)
        //then
        Assertions.assertEquals(mockedUsers.id, result.id)
        Assertions.assertEquals(mockedUsers.username, result.username)
        Assertions.assertEquals(mockedUsers.password, result.password)
        Assertions.assertEquals(mockedUsers.name, result.name)
        verify(exactly = 1) { usersRepository.save(any()) }
    }
    @Test
    fun `it should delete a user from the database`(){
        //given
        every { usersRepository.deleteById(userId!!) } just Runs

        //when
        usersService.deleteUser(userId!!)

        //then
        verify (exactly = 1){ usersRepository.deleteById(userId) }
    }
}