package br.com.devLearn.application.controller

import br.com.devLearn.application.controller.dtos.videos.*
import br.com.devLearn.application.controller.mappers.video.*
import br.com.devLearn.application.service.VideoService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/videos")
class VideoController(
    private val service: VideoService,
    private val updateMapper: UpdateVideoMapper,
    private val viewMapper: VideoViewMapper,
    private val viewListMapper: VideoViewListMapper,
    private val storeMapper: StoreVideoMapper
) {

    @PreAuthorize("hasRole('ROLE_VIEWER') or hasRole('ROLE_ADMIN')")
    @GetMapping
    fun listVideos(@RequestParam(required = false) courseName: String?): List<VideoViewDto>{
        return viewListMapper.map(service.listVideos(courseName))
    }

    @PreAuthorize("hasRole('ROLE_VIEWER') or hasRole('ROLE_ADMIN')")
    @GetMapping("{id}")
    fun findById(@PathVariable id: Long): VideoViewDto {
        return viewMapper.map(service.getVideoById(id))
    }

    @PreAuthorize("hasRole('CONTENT_CREATOR') or hasRole('ROLE_ADMIN')")
    @PostMapping
    fun saveVideo(@RequestBody @Valid dto: StoreVideoDto,
                  uriBuilder: UriComponentsBuilder): ResponseEntity<VideoViewDto>{
        val videoView = viewMapper.map(service.storeVideo(storeMapper.map(dto)))
        val uri = uriBuilder.path("/videos/${videoView.id}").build().toUri()
        return ResponseEntity.created(uri).body(videoView)
    }

    @PreAuthorize("hasRole('CONTENT_CREATOR') or hasRole('ROLE_ADMIN')")
    @PutMapping("{id}")
    fun updateVideo(@PathVariable id: Long,
                    @RequestBody @Valid videos: UpdateVideoDto): ResponseEntity<VideoViewDto>{
        val videoView = viewMapper.map(service.storeVideo(updateMapper.map(videos, service.getVideoById(id))))
        return ResponseEntity.ok(videoView)
    }

    @PreAuthorize("hasRole('CONTENT_CREATOR') or hasRole('ROLE_ADMIN')")
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteVideo(@PathVariable id: Long){
        service.deleteVideo(id)
    }
}