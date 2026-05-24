package com.github.gerdreiss.springcore.demo.common

import com.github.gerdreiss.springcore.util.DateProvider
import org.springframework.context.annotation.Lazy
import org.springframework.stereotype.Component
import java.time.format.DateTimeFormatter

@Component
//@Lazy
class TennisCoach : Coach {

    private val dateProvider: DateProvider

    constructor(dateProvider: DateProvider) {
        this.dateProvider = dateProvider
    }

    override fun getDailyWorkout(): String =
        "[${DateTimeFormatter.ISO_DATE.format(this.dateProvider.getCurrentDate())}]: Practice your backhand for 30 minutes"
}