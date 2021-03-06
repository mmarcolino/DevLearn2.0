package br.com.devLearn.application.controller.dtos.videos

import java.time.LocalDate

class VideoViewDto (
    var id: Long,
    var name: String,
    var description: String,
    var date: LocalDate,
    var url: String,
    var cursoId: Long
)