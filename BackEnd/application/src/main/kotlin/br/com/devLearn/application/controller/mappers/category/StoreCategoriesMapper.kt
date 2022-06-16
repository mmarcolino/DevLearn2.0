package br.com.devLearn.application.controller.mappers.category

import br.com.devLearn.application.controller.dtos.category.StoreCategoriesDto
import br.com.devLearn.application.controller.mappers.DefaultMapper
import br.com.devLearn.application.model.Category
import org.springframework.stereotype.Component

@Component
class StoreCategoriesMapper: DefaultMapper<StoreCategoriesDto, Category>  {
    override fun map(dto: StoreCategoriesDto): Category {
        return Category(
            name = dto.name
        )
    }
}