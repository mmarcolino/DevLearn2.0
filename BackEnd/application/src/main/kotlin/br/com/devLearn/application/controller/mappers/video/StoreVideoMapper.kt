package br.com.devLearn.application.controller.mappers.video

import br.com.devLearn.application.controller.dtos.videos.StoreVideoDto
import br.com.devLearn.application.controller.mappers.DefaultMapper
import br.com.devLearn.application.model.Video
import br.com.devLearn.application.service.CourseService
import org.springframework.stereotype.Component

@Component
class StoreVideoMapper(private val courseService: CourseService): DefaultMapper<StoreVideoDto, Video> {
    override fun map(dto: StoreVideoDto): Video {
        return Video(
            name = dto.name,
            description = dto.description,
            date = dto.date,
            url = dto.url,
            course = courseService.getCourseById(dto.cursoId!!)
        )
    }

}