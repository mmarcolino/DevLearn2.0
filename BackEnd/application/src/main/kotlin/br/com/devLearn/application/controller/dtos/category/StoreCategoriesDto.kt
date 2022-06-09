package br.com.devLearn.application.controller.dtos.category

import javax.validation.constraints.NotEmpty

class StoreCategoriesDto (
    @field:NotEmpty
    var name: String
)