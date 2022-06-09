package br.com.devLearn.application.controller.mappers.category

import br.com.devLearn.application.controller.dtos.category.CategoriesViewDto
import br.com.devLearn.application.controller.mappers.DefaultMapper
import br.com.devLearn.application.model.Categories
import org.springframework.stereotype.Component

@Component
class CategoriesViewListMapper: DefaultMapper<List<Categories>, List<CategoriesViewDto>> {
    override fun map(categoriesList: List<Categories>): List<CategoriesViewDto> {
        return categoriesList.map { Category -> CategoriesViewDto(
            id = Category.id!!,
            name = Category.name
        ) }
    }

}