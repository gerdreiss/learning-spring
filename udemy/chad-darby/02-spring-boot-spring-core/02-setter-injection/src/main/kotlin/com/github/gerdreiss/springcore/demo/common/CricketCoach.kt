package com.github.gerdreiss.springcore.demo.common

import com.github.gerdreiss.springcore.util.DateProvider
import org.springframework.stereotype.Component
import java.time.format.DateTimeFormatter

@Component
class CricketCoach : Coach {

    private val dateProvider: DateProvider

    constructor(dateProvider: DateProvider) {
        this.dateProvider = dateProvider
    }

    override fun getDailyWorkout(): String =
        "[${DateTimeFormatter.ISO_DATE.format(this.dateProvider.getCurrentDate())}]: Practice fast bowling for 15 minutes"
}