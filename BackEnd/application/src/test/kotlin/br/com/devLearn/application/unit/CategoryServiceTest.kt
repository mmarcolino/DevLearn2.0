package br.com.devLearn.application.unit

import br.com.devLearn.application.model.Category
import br.com.devLearn.application.repository.CategoryRepository
import br.com.devLearn.application.service.CategoryService
import io.mockk.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.random.Random

class CategoryServiceTest {

    private val categoryRepository: CategoryRepository = mockk(relaxed = true)
    private val categoryService = CategoryService(categoryRepository)
    private val mockedCategory = Category(Random.nextLong(0, Long.MAX_VALUE), "Backend")
    private val mockedCategory2 = Category(Random.nextLong(1, Long.MAX_VALUE), "FrontEnd")
    private val categoryId = this.mockedCategory.id

    @Test
    fun `it should return all registred categories`(){
        //given
        val lista: List<Category>  = listOf(mockedCategory, mockedCategory2)
        every { categoryRepository.findAll() } returns lista
        //when
        val result = categoryService.listCategories()
        //then
        verify (exactly = 1) { categoryRepository.findAll() }
        Assertions.assertEquals(lista[0].id, result[0].id)
        Assertions.assertEquals(lista[0].name, result[0].name)
        Assertions.assertEquals(lista[1].id, result[1].id)
        Assertions.assertEquals(lista[1].name, result[1].name)
    }

    @Test
    fun `it should return the registred category by id`(){
        //given
        every { categoryRepository.findById(categoryId!!) } returns Optional.of(mockedCategory)
        //when
        val result = categoryService.getCategoryById(categoryId!!)
        //then
        verify (exactly = 1) { categoryRepository.findById(categoryId!!) }
        Assertions.assertEquals(mockedCategory.id, result.id)
        Assertions.assertEquals(mockedCategory.name, result.name)
    }

    @Test
    fun `it should store and return a user from database`(){
        //given
        val newCategory = Category(
            id = mockedCategory.id,
            name = mockedCategory.name
        )
        every { categoryRepository.save(any()) } returns mockedCategory
        //when
        val result = categoryService.storeCategory(newCategory)
        //then
        Assertions.assertEquals(mockedCategory.id, result.id)
        Assertions.assertEquals(mockedCategory.name, result.name)
        verify (exactly = 1) { categoryRepository.save(any()) }
    }

    @Test
    fun `it should delete a category from the database`(){
        //given
        every { categoryRepository.deleteById(categoryId!!) } just Runs
        //when
        categoryService.deleteCategory(categoryId!!)
        //then
        verify (exactly = 1) { categoryRepository.deleteById(categoryId) }
    }
}