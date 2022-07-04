package br.com.devLearn.application.controller.mappers.video

import br.com.devLearn.application.controller.dtos.videos.VideoViewDto
import br.com.devLearn.application.controller.mappers.DefaultMapper
import br.com.devLearn.application.model.Video
import org.springframework.stereotype.Component

@Component
class VideoViewMapper: DefaultMapper<Video, VideoViewDto> {
    override fun map(video: Video): VideoViewDto {
        return VideoViewDto(
            id = video.id!!,
            name = video.name,
            description = video.description,
            date = video.date,
            url = video.url,
            cursoId = video.course.id!!
        )
    }
}