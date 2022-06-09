package br.com.devLearn.application.integration

import br.com.devLearn.application.model.Categories
import br.com.devLearn.application.repository.CategoriesRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class CategoriesRepositoryTest {
    @Autowired
    private lateinit var categoriesRepository: CategoriesRepository

    @AfterEach
    fun tearDown(){
        categoriesRepository.deleteAll()
    }

    @Test
    fun `should return all categorys after finding them`(){

        //given
        categoriesRepository.save(Categories(null, "Backend"))
        categoriesRepository.save(Categories(null, "Frontend"))

        //when
        val result = categoriesRepository.findAll()

        //then
        Assertions.assertEquals(2, result.size)
    }

    @Test
    fun `should return category after finding it by id`(){
        //given
        val subject = Categories(null, "Backend")
        val id = categoriesRepository.save(subject).id ?: throw java.lang.RuntimeException("Category id is null")
        //when
        val result = categoriesRepository.getById(id)
        //then
        Assertions.assertEquals(subject.id, result.id)
        Assertions.assertEquals(subject.name, result.name)
    }

    @Test
    fun `verify if category is truly deleted`(){
        //given
        val subject = Categories(null, "Backend")
        val id = categoriesRepository.save(subject).id ?: throw java.lang.RuntimeException("Category id is null")
        //when
        categoriesRepository.deleteById(id)
        //then
        Assertions.assertFalse(categoriesRepository.existsById(id))
    }

    @Test
    fun `verify if it return the caregory after saving it`(){
        //given
        val subject = Categories(null, "Backend")
        //when
        val result = categoriesRepository.save(subject)
        //then
        Assertions.assertEquals(subject.id, result.id)
        Assertions.assertEquals(subject.name, result.name)
    }
}