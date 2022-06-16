package br.com.devLearn.application.controller.mappers.category

import br.com.devLearn.application.controller.dtos.category.UpdateCategoriesDto
import br.com.devLearn.application.controller.mappers.UpdateMapper
import br.com.devLearn.application.model.Category
import org.springframework.stereotype.Component

@Component
class UpdateCategoriesMapper: UpdateMapper<UpdateCategoriesDto, Category> {
    override fun map(dto: UpdateCategoriesDto, category: Category): Category {
        if (!dto.name.isNullOrBlank()){
            category.name = dto.name!!
        }
        return category
    }

}