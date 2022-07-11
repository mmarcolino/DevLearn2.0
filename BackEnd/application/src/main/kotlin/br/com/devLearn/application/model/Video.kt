package br.com.devLearn.application.model

import java.time.LocalDate
import javax.persistence.*

@Entity
@Table(name = "video")
data class Video (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var name: String,
    val description: String,
    var date: LocalDate,
    var url: String,
    @ManyToOne
    val course: Course,
)