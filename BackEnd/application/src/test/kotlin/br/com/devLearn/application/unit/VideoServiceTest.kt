package br.com.devLearn.application.unit

import br.com.devLearn.application.model.*
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
    private val role = listOf(Role(1, "ADMIN"))
    private val authorEntity = User(Random.nextLong(0, Long.MAX_VALUE), "Kenma123", "12345678", "Kenma", role)
    private val categoryEntity = Category(Random.nextLong(0, Long.MAX_VALUE), "Backend")
    private val courseEntity = Course(Random.nextLong(0, Long.MAX_VALUE), "Spring Boot", "Curso de Spring Boot no Kotlin", authorEntity, categoryEntity)
    private val mockedVideo = Video(Random.nextLong(0, Long.MAX_VALUE), "Introdução", "Introdução sobre spring boot", LocalDate.now(), "https://www.youtube.com/watch?v=j5Tt8bmeCBA", courseEntity)
    private val mockedVideo2 = Video(Random.nextLong(0, Long.MAX_VALUE), "Segurança", "Um pouco sobre Spring Security", LocalDate.now(), "https://www.youtube.com/watch?v=Y25LDO6OLzQ", courseEntity)
    private val videoId = this.mockedVideo.id

    @Test
    fun `it should return all registred Videos`(){
        //given
        val lista: List<Video> = listOf(mockedVideo, mockedVideo2)
        every { videoRepository.findAll() } returns lista
        //when
        val result = videoService.listVideos("")
        //then
        verify (exactly = 1) { videoRepository.findAll() }
        Assertions.assertEquals(lista[0].id, result[0].id)
        Assertions.assertEquals(lista[0].name, result[0].name)
        Assertions.assertEquals(lista[0].course, result[0].course)
        Assertions.assertEquals(lista[0].url, result[0].url)
        Assertions.assertEquals(lista[0].date, result[0].date)
        Assertions.assertEquals(lista[0].description, result[0].description)
        Assertions.assertEquals(lista[1].id, result[1].id)
        Assertions.assertEquals(lista[1].name, result[1].name)
        Assertions.assertEquals(lista[1].course, result[1].course)
        Assertions.assertEquals(lista[1].url, result[1].url)
        Assertions.assertEquals(lista[1].date, result[1].date)
        Assertions.assertEquals(lista[1].description, result[1].description)
    }

    @Test
    fun `it should return all registred Videos with course name equal to x`(){
        //given
        val courseName = "teste"
        val lista: List<Video> = listOf(mockedVideo, mockedVideo2)
        every { videoRepository.findByCourseName(courseName) } returns lista
        //when
        val result = videoService.listVideos(courseName)
        //then
        verify (exactly = 1) { videoRepository.findByCourseName(courseName) }
        Assertions.assertEquals(lista[0].id, result[0].id)
        Assertions.assertEquals(lista[0].name, result[0].name)
        Assertions.assertEquals(lista[0].course, result[0].course)
        Assertions.assertEquals(lista[0].url, result[0].url)
        Assertions.assertEquals(lista[0].date, result[0].date)
        Assertions.assertEquals(lista[0].description, result[0].description)
        Assertions.assertEquals(lista[1].id, result[1].id)
        Assertions.assertEquals(lista[1].name, result[1].name)
        Assertions.assertEquals(lista[1].course, result[1].course)
        Assertions.assertEquals(lista[1].url, result[1].url)
        Assertions.assertEquals(lista[1].date, result[1].date)
        Assertions.assertEquals(lista[1].description, result[1].description)
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
        Assertions.assertEquals(mockedVideo.course, result.course)
        verify (exactly = 1){ videoRepository.findById(videoId!!)}
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
            course = mockedVideo.course
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
        Assertions.assertEquals(mockedVideo.course, result.course)
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