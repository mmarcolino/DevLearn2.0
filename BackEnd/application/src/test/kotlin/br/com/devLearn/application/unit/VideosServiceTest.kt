package br.com.devLearn.application.unit

import br.com.devLearn.application.model.Categories
import br.com.devLearn.application.model.Courses
import br.com.devLearn.application.model.Users
import br.com.devLearn.application.model.Videos
import br.com.devLearn.application.repository.VideosRepository
import br.com.devLearn.application.service.VideosService
import io.mockk.*
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import java.sql.Date
import java.time.LocalDate
import java.util.*
import kotlin.random.Random


class VideosServiceTest {

    private val videosRepository: VideosRepository = mockk(relaxed = true)
    private val videosService = VideosService(videosRepository)
    private val authorEntity = Users(Random.nextLong(0, Long.MAX_VALUE), "Kenma123", "12345678", "Kenma")
    private val categoriesEntity = Categories(Random.nextLong(0, Long.MAX_VALUE), "Backend")
    private val courseEntity = Courses(Random.nextLong(0, Long.MAX_VALUE), "Spring Boot", "Curso de Spring Boot no Kotlin", authorEntity, categoriesEntity)
    private val mockedVideo = Videos(Random.nextLong(0, Long.MAX_VALUE), "Introdução", "Introdução sobre spring boot", LocalDate.now(), "https://www.youtube.com/watch?v=j5Tt8bmeCBA", courseEntity)
    private val videoId = this.mockedVideo.id

    @Test
    fun `it should return all registred Videos`(){
        //given
        every { videosRepository.findAll() } returns emptyList()
        //when
        val result = videosService.listVideos()
        //then
        verify (exactly = 1) { videosRepository.findAll() }
    }

    @Test
    fun `it should return the registred video by id`(){
        //given
        every { videosRepository.findById(videoId!!) } returns Optional.of(mockedVideo)
        //when
        val result = videosService.getVideosById(videoId!!)
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
        val newVideo = Videos(
            id = mockedVideo.id,
            name = mockedVideo.name,
            description = mockedVideo.description,
            date = mockedVideo.date,
            url = mockedVideo.url,
            curso = mockedVideo.curso
        )
        every { videosRepository.save(any()) } returns mockedVideo
        //when
        val result = videosService.storeVideo(newVideo)
        //then
        Assertions.assertEquals(mockedVideo.id, result.id)
        Assertions.assertEquals(mockedVideo.name, result.name)
        Assertions.assertEquals(mockedVideo.description, result.description)
        Assertions.assertEquals(mockedVideo.date, result.date)
        Assertions.assertEquals(mockedVideo.url, result.url)
        Assertions.assertEquals(mockedVideo.curso, result.curso)
        verify (exactly = 1){ videosRepository.save(any()) }
    }

    @Test
    fun `it should delete a video from the database`(){
        //given
        every { videosRepository.deleteById(videoId!!) }just Runs
        //when
        videosService.deleteVideo(videoId!!)
        //then
        verify (exactly = 1){ videosRepository.deleteById(videoId!!) }
    }

}