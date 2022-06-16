package br.com.devLearn.application.model

import javax.persistence.*

@Entity
@Table(name = "category")
data class Category(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var name: String,
)