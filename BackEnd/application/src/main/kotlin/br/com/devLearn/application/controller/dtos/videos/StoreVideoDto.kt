package br.com.devLearn.application.controller.dtos.videos

import java.time.LocalDate
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.NotNull

class StoreVideoDto (
    @field:NotEmpty
    var name: String,
    @field:NotEmpty
    var description: String,
    var date: LocalDate,
    @field:NotEmpty
    var url: String,
    @field:NotNull
    var cursoId: Long,
)