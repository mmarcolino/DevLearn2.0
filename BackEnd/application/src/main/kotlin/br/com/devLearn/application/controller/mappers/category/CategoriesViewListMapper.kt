package br.com.devLearn.application.controller.mappers.category

import br.com.devLearn.application.controller.dtos.category.CategoriesViewDto
import br.com.devLearn.application.controller.mappers.DefaultMapper
import br.com.devLearn.application.model.Category
import org.springframework.stereotype.Component

@Component
class CategoriesViewListMapper: DefaultMapper<List<Category>, List<CategoriesViewDto>> {
    override fun map(categoryList: List<Category>): List<CategoriesViewDto> {
        return categoryList.map { Category -> CategoriesViewDto(
            id = Category.id!!,
            name = Category.name
        ) }
    }

}