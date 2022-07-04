package br.com.devLearn.application.model

import javax.persistence.*

@Entity
@Table(name = "Roles")
class Role (
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    var name: String
)