package br.com.devLearn.application.controller

import br.com.devLearn.application.controller.dtos.videos.*
import br.com.devLearn.application.controller.mappers.videos.*
import br.com.devLearn.application.service.VideosService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/videos")
class VideosController(
    private val service: VideosService,
    private val updateMapper: UpdateVideosMapper,
    private val viewMapper: VideosViewMapper,
    private val viewListMapper: VideosViewListMapper,
    private val storeMapper: StoreVideosMapper
) {

    @GetMapping
    fun listVideos(@RequestParam(required = false) courseName: String?): List<VideosViewDto>{
        return viewListMapper.map(service.listVideos(courseName))
    }

    @GetMapping("{id}")
    fun findById(@PathVariable id: Long): VideosViewDto {
        return viewMapper.map(service.getVideosById(id))
    }

    @PostMapping
    fun saveVideo(@RequestBody @Valid dto: StoreVideosDto,
                  uriBuilder: UriComponentsBuilder): ResponseEntity<VideosViewDto>{
        val videoView = viewMapper.map(service.storeVideo(storeMapper.map(dto)))
        val uri = uriBuilder.path("/videos/${videoView.id}").build().toUri()
        return ResponseEntity.created(uri).body(videoView)
    }

    @PutMapping("{id}")
    fun updateVideo(@PathVariable id: Long,
                    @RequestBody @Valid videos: UpdateVideoDto): ResponseEntity<VideosViewDto>{
        val videoView = viewMapper.map(service.storeVideo(updateMapper.map(videos, service.getVideosById(id))))
        return ResponseEntity.ok(videoView)
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteVideo(@PathVariable id: Long){
        service.deleteVideo(id)
    }
}