package br.com.devLearn.application.service

import br.com.devLearn.application.excpetion.NotFoundException
import br.com.devLearn.application.model.Video
import br.com.devLearn.application.repository.VideoRepository
import org.springframework.stereotype.Service

@Service
class VideoService (private val repository: VideoRepository){
    fun listVideos(courseName: String?): List <Video>{
        val videos = if(courseName.isNullOrBlank())
            repository.findAll()
        else
            repository.findByCourseName(courseName)
        return repository.findAll()
    }

    fun getVideoById(id: Long): Video{
        return repository.findById(id).orElseThrow { NotFoundException("Video não encontrado") }
    }

    fun storeVideo(video: Video): Video{
        repository.save(video)
        return video
    }

    fun deleteVideo(id: Long){
        repository.deleteById(id)
    }
}