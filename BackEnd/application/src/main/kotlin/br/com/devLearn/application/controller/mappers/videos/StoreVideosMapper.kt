package br.com.devLearn.application.controller.mappers.videos

import br.com.devLearn.application.controller.dtos.videos.StoreVideosDto
import br.com.devLearn.application.controller.mappers.DefaultMapper
import br.com.devLearn.application.model.Videos
import br.com.devLearn.application.service.CoursesService
import org.springframework.stereotype.Component

@Component
class StoreVideosMapper(private val coursesService: CoursesService): DefaultMapper<StoreVideosDto, Videos> {
    override fun map(dto: StoreVideosDto): Videos {
        return Videos(
            name = dto.name,
            description = dto.description,
            date = dto.date,
            url = dto.url,
            curso = coursesService.getCourseById(dto.cursoId!!)
        )
    }

}