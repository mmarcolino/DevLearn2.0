package br.com.devLearn.application.service

import br.com.devLearn.application.excpetion.NotFoundException
import br.com.devLearn.application.model.Categories
import br.com.devLearn.application.repository.CategoriesRepository
import org.springframework.stereotype.Service

@Service
class CategoriesService(private val repository: CategoriesRepository,
                        private val NOT_FOUND_MESSAGE: String = "Categoria não ecnontrada") {

    fun listCategories(): List <Categories>{
        return repository.findAll()
    }

    fun getCategoryById(id: Long): Categories{
        return repository.findById(id).orElseThrow { NotFoundException(NOT_FOUND_MESSAGE) }
    }

    fun storeCategory(categories: Categories): Categories{
        repository.save(categories)
        return categories
    }

    fun deleteCategory(id: Long){
        repository.deleteById(id)
    }
}