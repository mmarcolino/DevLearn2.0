package br.com.devLearn.application.repository

import br.com.devLearn.application.model.Videos
import org.springframework.data.jpa.repository.JpaRepository

interface VideosRepository:JpaRepository<Videos, Long> {
}