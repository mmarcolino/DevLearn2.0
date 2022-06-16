package br.com.devLearn.application.controller.mappers.videos

import br.com.devLearn.application.controller.dtos.videos.StoreVideosDto
import br.com.devLearn.application.controller.mappers.DefaultMapper
import br.com.devLearn.application.model.Video
import br.com.devLearn.application.service.CourseService
import org.springframework.stereotype.Component

@Component
class StoreVideosMapper(private val courseService: CourseService): DefaultMapper<StoreVideosDto, Video> {
    override fun map(dto: StoreVideosDto): Video {
        return Video(
            name = dto.name,
            description = dto.description,
            date = dto.date,
            url = dto.url,
            curso = courseService.getCourseById(dto.cursoId!!)
        )
    }

}