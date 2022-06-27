package br.com.devLearn.application.controller.dtos.category

import javax.validation.constraints.NotEmpty

class StoreCategoryDto (
    @field:NotEmpty
    var name: String
)