package com.github.gerdreiss.actuator.demo.rest

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DemoRestController {
    // expose "/" that return "Hello World"
    @GetMapping("/")
    fun sayHello(): String = "Hello World!"

    // expose a new endpoint for "workout"
    @GetMapping("/workout")
    fun dailyWorkout(): String = "Run a hard 5k!"

    // expose a new endpoint for "fortune"
    @GetMapping("/fortune")
    fun dailyFortune(): String = "Today is your lucky day."
}