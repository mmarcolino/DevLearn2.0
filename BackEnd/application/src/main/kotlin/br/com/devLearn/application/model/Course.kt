package br.com.devLearn.application.model

import javax.persistence.*

@Entity
data class Course(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var name: String,
    val description: String,
    @ManyToOne
    val author: User,
    @ManyToOne
    var category: Category,
)