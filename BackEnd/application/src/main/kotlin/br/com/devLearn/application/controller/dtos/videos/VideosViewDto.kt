package br.com.devLearn.application.controller.dtos.videos

import java.time.LocalDate

class VideosViewDto (
    var id: Long,
    var name: String,
    var description: String,
    var date: LocalDate,
    var url: String,
    var cursoId: Long
)