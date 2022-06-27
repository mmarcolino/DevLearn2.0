package br.com.devLearn.application.controller.mappers.video

import br.com.devLearn.application.controller.dtos.videos.VideoViewDto
import br.com.devLearn.application.controller.mappers.DefaultMapper
import br.com.devLearn.application.model.Video
import org.springframework.stereotype.Component

@Component
class VideoViewListMapper: DefaultMapper<List<Video>, List<VideoViewDto>> {

    override fun map(videoList: List<Video>): List<VideoViewDto> {
        return videoList.map { Videos -> VideoViewDto(
            id = Videos.id!!,
            name = Videos.name,
            description = Videos.description,
            date = Videos.date,
            url = Videos.url,
            cursoId = Videos.course.id!!
        ) }
    }
}