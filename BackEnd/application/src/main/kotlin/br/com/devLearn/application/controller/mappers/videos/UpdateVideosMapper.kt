package br.com.devLearn.application.controller.mappers.videos

import br.com.devLearn.application.controller.dtos.videos.UpdateVideoDto
import br.com.devLearn.application.controller.mappers.UpdateMapper
import br.com.devLearn.application.model.Video
import br.com.devLearn.application.service.CourseService
import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class UpdateVideosMapper(private val courseService: CourseService): UpdateMapper<UpdateVideoDto, Video> {
    override fun map(dto: UpdateVideoDto, video: Video): Video {
        if(!dto.name.isNullOrBlank()){
            video.name = dto.name!!
        }
        if(!dto.description.isNullOrBlank()){
            video.name = dto.name!!
        }
        if (!dto.url.isNullOrBlank()){
            video.url = dto.url!!
        }
        video.date = LocalDate.now()

        return video
    }

}
