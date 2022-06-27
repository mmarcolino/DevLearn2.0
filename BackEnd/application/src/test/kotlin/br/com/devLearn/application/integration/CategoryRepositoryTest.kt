package br.com.devLearn.application.integration

import br.com.devLearn.application.model.Category
import br.com.devLearn.application.repository.CategoryRepository
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest

@DataJpaTest
class CategoryRepositoryTest {
    @Autowired
    private lateinit var categoryRepository: CategoryRepository

    @AfterEach
    fun tearDown(){
        categoryRepository.deleteAll()
    }

    @Test
    fun `should return all categorys after finding them`(){

        //given
        val subject = categoryRepository.save(Category(null, "Backend"))
        val subject2 = categoryRepository.save(Category(null, "Frontend"))

        //when
        val result = categoryRepository.findAll()

        //then
        Assertions.assertEquals(2, result.size)
        Assertions.assertEquals(subject.id, result[0].id)
        Assertions.assertEquals(subject.name, result[0].name)
        Assertions.assertEquals(subject2.id, result[1].id)
        Assertions.assertEquals(subject2.name, result[1].name)
    }

    @Test
    fun `should return category after finding it by id`(){
        //given
        val subject = Category(null, "Backend")
        val id = categoryRepository.save(subject).id ?: throw java.lang.RuntimeException("Category id is null")
        //when
        val result = categoryRepository.getById(id)
        //then
        Assertions.assertEquals(subject.id, result.id)
        Assertions.assertEquals(subject.name, result.name)
    }

    @Test
    fun `verify if category is truly deleted`(){
        //given
        val subject = Category(null, "Backend")
        val id = categoryRepository.save(subject).id ?: throw java.lang.RuntimeException("Category id is null")
        //when
        categoryRepository.deleteById(id)
        //then
        Assertions.assertFalse(categoryRepository.existsById(id))
    }

    @Test
    fun `verify if it return the caregory after saving it`(){
        //given
        val subject = Category(null, "Backend")
        //when
        val result = categoryRepository.save(subject)
        //then
        Assertions.assertEquals(subject.id, result.id)
        Assertions.assertEquals(subject.name, result.name)
    }
}