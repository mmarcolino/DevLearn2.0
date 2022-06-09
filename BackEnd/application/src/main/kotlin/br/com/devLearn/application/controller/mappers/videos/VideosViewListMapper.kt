package br.com.devLearn.application.controller.mappers.videos

import br.com.devLearn.application.controller.dtos.videos.VideosViewDto
import br.com.devLearn.application.controller.mappers.DefaultMapper
import br.com.devLearn.application.model.Videos
import org.springframework.stereotype.Component

@Component
class VideosViewListMapper: DefaultMapper<List<Videos>, List<VideosViewDto>> {

    override fun map(videoList: List<Videos>): List<VideosViewDto> {
        return videoList.map { Videos -> VideosViewDto(
            id = Videos.id!!,
            name = Videos.name,
            description = Videos.description,
            date = Videos.date,
            url = Videos.url,
            cursoId = Videos.curso.id!!
        ) }
    }
}