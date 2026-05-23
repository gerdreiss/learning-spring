package com.github.gerdreiss.springcore.demo

import org.springframework.stereotype.Component

@Component
class CricketCoach : Coach {
    override fun getDailyWorkout(): String = "Practice fast bowling for 15 minutes"
}