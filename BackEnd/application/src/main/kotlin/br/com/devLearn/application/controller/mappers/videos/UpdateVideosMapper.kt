package br.com.devLearn.application.controller.mappers.videos

import br.com.devLearn.application.controller.dtos.videos.UpdateVideoDto
import br.com.devLearn.application.controller.mappers.UpdateMapper
import br.com.devLearn.application.model.Videos
import br.com.devLearn.application.service.CoursesService
import org.springframework.stereotype.Component
import java.sql.Date
import java.time.LocalDate

@Component
class UpdateVideosMapper(private val coursesService: CoursesService): UpdateMapper<UpdateVideoDto, Videos> {
    override fun map(dto: UpdateVideoDto, video: Videos): Videos {
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
