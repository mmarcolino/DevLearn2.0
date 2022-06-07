package br.com.devLearn.application.controller.dtos.courses

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

class CourseStoreDto(
    @field:NotEmpty
    var name:String,
    @field:NotEmpty
    var description: String,
    @field:NotNull
    var authorId: Long,
    @field:NotNull
    var categoryId: Long
)


