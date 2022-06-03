package br.com.devLearn.application.model

import javax.persistence.*

@Entity
data class Courses(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    val name: String,
    val desription: String,
    @ManyToOne
    val author: User,
    @ManyToOne
    val category: Category
)