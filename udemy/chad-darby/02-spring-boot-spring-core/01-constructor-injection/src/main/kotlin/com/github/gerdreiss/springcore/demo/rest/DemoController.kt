package com.github.gerdreiss.springcore.demo.rest

import com.github.gerdreiss.springcore.demo.common.Coach
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DemoController {

    private val coach: Coach

    @Autowired
    constructor(@Qualifier("aquatic") coach: Coach) {
        this.coach = coach
    }

    @GetMapping("/daily-workout")
    fun getDailyWorkout(): String = this.coach.getDailyWorkout()

}