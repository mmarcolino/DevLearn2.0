package br.com.devLearn.application.controller.mappers.category

import br.com.devLearn.application.controller.dtos.category.UpdateCategoryDto
import br.com.devLearn.application.controller.mappers.UpdateMapper
import br.com.devLearn.application.model.Category
import org.springframework.stereotype.Component

@Component
class UpdateCategoryMapper: UpdateMapper<UpdateCategoryDto, Category> {
    override fun map(dto: UpdateCategoryDto, category: Category): Category {
        if (!dto.name.isNullOrBlank()){
            category.name = dto.name!!
        }
        return category
    }

}