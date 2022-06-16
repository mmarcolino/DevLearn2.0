package br.com.devLearn.application.unit

import br.com.devLearn.application.model.Category
import br.com.devLearn.application.model.Course
import br.com.devLearn.application.model.User
import br.com.devLearn.application.model.Video
import br.com.devLearn.application.repository.VideoRepository
import br.com.devLearn.application.service.VideoService
import io.mockk.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.time.LocalDate
import java.util.*
import kotlin.random.Random


class VideoServiceTest {

    private val videoRepository: VideoRepository = mockk(relaxed = true)
    private val videoService = VideoService(videoRepository)
    private val authorEntity = User(Random.nextLong(0, Long.MAX_VALUE), "Kenma123", "12345678", "Kenma")
    private val categoryEntity = Category(Random.nextLong(0, Long.MAX_VALUE), "Backend")
    private val courseEntity = Course(Random.nextLong(0, Long.MAX_VALUE), "Spring Boot", "Curso de Spring Boot no Kotlin", authorEntity, categoryEntity)
    private val mockedVideo = Video(Random.nextLong(0, Long.MAX_VALUE), "Introdução", "Introdução sobre spring boot", LocalDate.now(), "https://www.youtube.com/watch?v=j5Tt8bmeCBA", courseEntity)
    private val videoId = this.mockedVideo.id

    @Test
    fun `it should return all registred Videos`(){
        //given
        every { videoRepository.findAll() } returns emptyList()
        //when
        val result = videoService.listVideos("")
        //then
        verify (exactly = 1) { videoRepository.findAll() }
    }

    @Test
    fun `it should return all registred Videos with course name equal to x`(){
        //given
        val courseName = "teste"
        every { videoRepository.findByCoursesName(courseName) } returns emptyList()
        //when
        val result = videoService.listVideos(courseName)
        //then
        verify (exactly = 1) { videoRepository.findByCoursesName(courseName) }
    }


    @Test
    fun `it should return the registred video by id`(){
        //given
        every { videoRepository.findById(videoId!!) } returns Optional.of(mockedVideo)
        //when
        val result = videoService.getVideoById(videoId!!)
        //then
        Assertions.assertEquals(mockedVideo.id, result.id)
        Assertions.assertEquals(mockedVideo.name, result.name)
        Assertions.assertEquals(mockedVideo.description, result.description)
        Assertions.assertEquals(mockedVideo.date, result.date)
        Assertions.assertEquals(mockedVideo.url, result.url)
        Assertions.assertEquals(mockedVideo.curso, result.curso)
    }

    @Test
    fun `it should store and return a video from the database`(){
        //given
        val newVideo = Video(
            id = mockedVideo.id,
            name = mockedVideo.name,
            description = mockedVideo.description,
            date = mockedVideo.date,
            url = mockedVideo.url,
            curso = mockedVideo.curso
        )
        every { videoRepository.save(any()) } returns mockedVideo
        //when
        val result = videoService.storeVideo(newVideo)
        //then
        Assertions.assertEquals(mockedVideo.id, result.id)
        Assertions.assertEquals(mockedVideo.name, result.name)
        Assertions.assertEquals(mockedVideo.description, result.description)
        Assertions.assertEquals(mockedVideo.date, result.date)
        Assertions.assertEquals(mockedVideo.url, result.url)
        Assertions.assertEquals(mockedVideo.curso, result.curso)
        verify (exactly = 1){ videoRepository.save(any()) }
    }

    @Test
    fun `it should delete a video from the database`(){
        //given
        every { videoRepository.deleteById(videoId!!) }just Runs
        //when
        videoService.deleteVideo(videoId!!)
        //then
        verify (exactly = 1){ videoRepository.deleteById(videoId!!) }
    }

}