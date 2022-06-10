package br.com.devLearn.application.service

import br.com.devLearn.application.excpetion.NotFoundException
import br.com.devLearn.application.model.Videos
import br.com.devLearn.application.repository.VideosRepository
import org.springframework.stereotype.Service

@Service
class VideosService (private val repository: VideosRepository,
                     private val NOT_FOUND_MESSAGE: String = "Video não encontrado"){
    fun listVideos(courseName: String?): List <Videos>{
        val videos = if(courseName.isNullOrBlank())
            repository.findAll()
        else
            repository.findByCoursesName(courseName)
        return repository.findAll()
    }

    fun getVideosById(id: Long): Videos{
        return repository.findById(id).orElseThrow { NotFoundException(NOT_FOUND_MESSAGE) }
    }

    fun storeVideo(video: Videos): Videos{
        repository.save(video)
        return video
    }

    fun deleteVideo(id: Long){
        repository.deleteById(id)
    }
}