package br.com.devLearn.application.controller.mappers

interface DefaultMapper <T, U>{
    fun map(t:T): U
}