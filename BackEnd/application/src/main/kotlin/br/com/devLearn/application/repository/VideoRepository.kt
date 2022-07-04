package br.com.devLearn.application.repository

import br.com.devLearn.application.model.Video
import org.springframework.data.jpa.repository.JpaRepository

interface VideoRepository:JpaRepository<Video, Long> {
   fun findByCourseName(coursesName: String):List<Video>?
}