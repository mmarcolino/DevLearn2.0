package com.devlearn.security.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("url-authorize")
class UrlController {

    @PostMapping
    fun validateUrl(@RequestBody url: String): ResponseEntity<String> {
        return ResponseEntity.ok("Url validada")
    }

}