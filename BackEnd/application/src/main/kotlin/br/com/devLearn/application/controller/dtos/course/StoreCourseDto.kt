package br.com.devLearn.application.controller.dtos.course

import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

class StoreCourseDto(
    @field:NotEmpty
    var name:String,
    @field:NotEmpty
    var description: String,
    @field:NotNull
    var authorId: Long,
    @field:NotNull
    var categoryId: Long
)


