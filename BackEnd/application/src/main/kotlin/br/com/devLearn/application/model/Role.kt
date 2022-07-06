package br.com.devLearn.application.model

import javax.persistence.*

@Entity
@Table(name = "role")
class Role (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var name: String
)