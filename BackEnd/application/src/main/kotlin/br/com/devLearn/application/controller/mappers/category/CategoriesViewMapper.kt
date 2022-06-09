package br.com.devLearn.application.controller.mappers.category

import br.com.devLearn.application.controller.dtos.category.CategoriesViewDto
import br.com.devLearn.application.controller.mappers.DefaultMapper
import br.com.devLearn.application.model.Categories
import org.springframework.stereotype.Component

@Component
class CategoriesViewMapper: DefaultMapper<Categories, CategoriesViewDto> {
    override fun map(categories: Categories): CategoriesViewDto {
        return CategoriesViewDto(
            id = categories.id!!,
            name = categories.name
        )
    }

}