package br.com.devLearn.application.model

import java.util.Date
import javax.persistence.*

@Entity
data class Videos (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val description: String,
    val date: Date,
    val url: String,
    @ManyToOne
    val curso: Courses,
)