package br.com.devLearn.application.controller.dtos.category

import javax.validation.constraints.NotEmpty

class CategoryStoreDto (
    @field:NotEmpty
    var name: String
)