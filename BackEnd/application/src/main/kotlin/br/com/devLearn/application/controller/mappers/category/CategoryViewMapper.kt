package br.com.devLearn.application.controller.mappers.category

import br.com.devLearn.application.controller.dtos.category.CategoryViewDto
import br.com.devLearn.application.controller.mappers.DefaultMapper
import br.com.devLearn.application.model.Category
import org.springframework.stereotype.Component

@Component
class CategoryViewMapper: DefaultMapper<Category, CategoryViewDto> {
    override fun map(category: Category): CategoryViewDto {
        return CategoryViewDto(
            id = category.id!!,
            name = category.name
        )
    }

}