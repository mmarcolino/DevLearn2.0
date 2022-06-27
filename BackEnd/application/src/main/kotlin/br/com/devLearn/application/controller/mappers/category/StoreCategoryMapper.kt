package br.com.devLearn.application.controller.mappers.category

import br.com.devLearn.application.controller.dtos.category.StoreCategoryDto
import br.com.devLearn.application.controller.mappers.DefaultMapper
import br.com.devLearn.application.model.Category
import org.springframework.stereotype.Component

@Component
class StoreCategoryMapper: DefaultMapper<StoreCategoryDto, Category>  {
    override fun map(dto: StoreCategoryDto): Category {
        return Category(
            name = dto.name
        )
    }
}