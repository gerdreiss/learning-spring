package com.github.gerdreiss.springboot.demo.rest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class FunRestController {
    // expose "/" that returns "Hello, World!"

    @GetMapping("/")
    fun sayHello(): String = "Hello, World!"
}