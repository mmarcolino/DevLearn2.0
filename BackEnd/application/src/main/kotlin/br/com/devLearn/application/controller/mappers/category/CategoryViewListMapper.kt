package br.com.devLearn.application.controller.mappers.category

import br.com.devLearn.application.controller.dtos.category.CategoryViewDto
import br.com.devLearn.application.controller.mappers.DefaultMapper
import br.com.devLearn.application.model.Category
import org.springframework.stereotype.Component

@Component
class CategoryViewListMapper: DefaultMapper<List<Category>, List<CategoryViewDto>> {
    override fun map(categoryList: List<Category>): List<CategoryViewDto> {
        return categoryList.map { Category -> CategoryViewDto(
            id = Category.id!!,
            name = Category.name
        ) }
    }

}