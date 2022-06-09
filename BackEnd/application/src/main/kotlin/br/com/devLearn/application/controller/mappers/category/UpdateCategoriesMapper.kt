package br.com.devLearn.application.controller.mappers.category

import br.com.devLearn.application.controller.dtos.category.UpdateCategoriesDto
import br.com.devLearn.application.controller.mappers.UpdateMapper
import br.com.devLearn.application.model.Categories
import org.springframework.stereotype.Component

@Component
class UpdateCategoriesMapper: UpdateMapper<UpdateCategoriesDto, Categories> {
    override fun map(dto: UpdateCategoriesDto, categories: Categories): Categories {
        if (!dto.name.isNullOrBlank()){
            categories.name = dto.name!!
        }
        return categories
    }

}