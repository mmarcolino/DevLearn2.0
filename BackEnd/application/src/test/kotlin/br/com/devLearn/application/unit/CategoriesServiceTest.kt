package br.com.devLearn.application.unit

import br.com.devLearn.application.model.Categories
import br.com.devLearn.application.repository.CategoriesRepository
import br.com.devLearn.application.service.CategoriesService
import io.mockk.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.random.Random

class CategoriesServiceTest {

    private val categoriesRepository: CategoriesRepository = mockk(relaxed = true)
    private val categoriesService = CategoriesService(categoriesRepository)
    private val mockedCategories = Categories(Random.nextLong(0, Long.MAX_VALUE), "Backend")
    private val categoryId = this.mockedCategories.id

    @Test
    fun `it should return all registred categories`(){
        //given
        every { categoriesRepository.findAll() } returns  emptyList()
        //when
        val result = categoriesService.listCategories()
        //then
        verify (exactly = 1) { categoriesRepository.findAll() }
    }

    @Test
    fun `it should return the registred category by id`(){
        //given
        every { categoriesRepository.findById(categoryId!!) } returns Optional.of(mockedCategories)
        //when
        val result = categoriesService.getCategoryById(categoryId!!)
        //then
        Assertions.assertEquals(mockedCategories.id, result.id)
        Assertions.assertEquals(mockedCategories.name, result.name)
    }

    @Test
    fun `it should store and return a user from database`(){
        //given
        val newCategories = Categories(
            id = mockedCategories.id,
            name = mockedCategories.name
        )
        every { categoriesRepository.save(any()) } returns mockedCategories
        //when
        val result = categoriesService.storeCategory(newCategories)
        //then
        Assertions.assertEquals(mockedCategories.id, result.id)
        Assertions.assertEquals(mockedCategories.name, result.name)
        verify (exactly = 1) { categoriesRepository.save(any()) }
    }

    @Test
    fun `it should delete a category from the database`(){
        //given
        every { categoriesRepository.deleteById(categoryId!!) } just Runs
        //when
        categoriesService.deleteCategory(categoryId!!)
        //then
        verify (exactly = 1) { categoriesRepository.deleteById(categoryId) }
    }
}