package br.com.devLearn.application.controller.mappers.category

import br.com.devLearn.application.controller.dtos.category.CategoryStoreDto
import br.com.devLearn.application.controller.mappers.DefaultMapper
import br.com.devLearn.application.model.Category
import org.springframework.stereotype.Component

@Component
class CategoryStoreMapper: DefaultMapper<CategoryStoreDto, Category>  {
    override fun map(dto: CategoryStoreDto): Category {
        return Category(
            name = dto.name
        )
    }
}