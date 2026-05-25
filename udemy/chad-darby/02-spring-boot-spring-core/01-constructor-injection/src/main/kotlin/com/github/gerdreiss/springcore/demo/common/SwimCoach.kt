package com.github.gerdreiss.springcore.demo.common

import com.github.gerdreiss.springcore.util.DateProvider
import java.time.format.DateTimeFormatter

class SwimCoach : Coach {
    private val dateProvider: DateProvider

    constructor(dateProvider: DateProvider) {
        this.dateProvider = dateProvider
    }

    override fun getDailyWorkout(): String =
        "[${DateTimeFormatter.ISO_DATE.format(this.dateProvider.getCurrentDate())}]: Swim 5x100m"
}