package br.com.devLearn.application.controller.mappers.category

import br.com.devLearn.application.controller.dtos.category.CategoriesViewDto
import br.com.devLearn.application.controller.mappers.DefaultMapper
import br.com.devLearn.application.model.Category
import org.springframework.stereotype.Component

@Component
class CategoriesViewMapper: DefaultMapper<Category, CategoriesViewDto> {
    override fun map(category: Category): CategoriesViewDto {
        return CategoriesViewDto(
            id = category.id!!,
            name = category.name
        )
    }

}