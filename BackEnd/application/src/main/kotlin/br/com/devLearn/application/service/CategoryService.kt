package br.com.devLearn.application.service

import br.com.devLearn.application.excpetion.NotFoundException
import br.com.devLearn.application.model.Category
import br.com.devLearn.application.repository.CategoryRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(private val repository: CategoryRepository) {

    fun listCategories(): List <Category>{
        return repository.findAll()
    }

    fun getCategoryById(id: Long): Category{
        return repository.findById(id).orElseThrow { NotFoundException("Categoria não ecnontrada") }
    }

    fun storeCategory(category: Category): Category{
        repository.save(category)
        return category
    }

    fun deleteCategory(id: Long){
        repository.deleteById(id)
    }
}