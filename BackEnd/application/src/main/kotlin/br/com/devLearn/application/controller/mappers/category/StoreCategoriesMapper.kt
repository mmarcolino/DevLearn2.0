package br.com.devLearn.application.controller.mappers.category

import br.com.devLearn.application.controller.dtos.category.StoreCategoriesDto
import br.com.devLearn.application.controller.mappers.DefaultMapper
import br.com.devLearn.application.model.Categories
import org.springframework.stereotype.Component

@Component
class StoreCategoriesMapper: DefaultMapper<StoreCategoriesDto, Categories>  {
    override fun map(dto: StoreCategoriesDto): Categories {
        return Categories(
            name = dto.name
        )
    }
}