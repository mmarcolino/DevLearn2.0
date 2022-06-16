package br.com.devLearn.application.controller.mappers.videos

import br.com.devLearn.application.controller.dtos.videos.VideosViewDto
import br.com.devLearn.application.controller.mappers.DefaultMapper
import br.com.devLearn.application.model.Video
import org.springframework.stereotype.Component

@Component
class VideosViewMapper: DefaultMapper<Video, VideosViewDto> {
    override fun map(video: Video): VideosViewDto {
        return VideosViewDto(
            id = video.id!!,
            name = video.name,
            description = video.description,
            date = video.date,
            url = video.url,
            cursoId = video.curso.id!!
        )
    }
}