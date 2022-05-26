package br.com.devLearn.application.controller.mappers

interface UpdateMapper <T, U> {
    fun map(t:T, u:U)
}