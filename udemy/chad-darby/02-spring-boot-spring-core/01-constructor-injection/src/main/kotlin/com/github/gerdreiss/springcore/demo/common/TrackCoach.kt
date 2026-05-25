package com.github.gerdreiss.springcore.demo.common

import com.github.gerdreiss.springcore.util.DateProvider
import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Primary
import org.springframework.stereotype.Component
import java.time.format.DateTimeFormatter

@Component
@Primary
class TrackCoach : Coach {

    private val dateProvider: DateProvider

    private val logger: Logger? = LoggerFactory.getLogger(javaClass)

    constructor(dateProvider: DateProvider) {
        this.dateProvider = dateProvider
    }

    @PostConstruct
    fun init() = logger?.info("track coach initialized")

    @PreDestroy
    fun destroy() = logger?.info("destroying track coach")

    override fun getDailyWorkout(): String =
        "[${DateTimeFormatter.ISO_DATE.format(this.dateProvider.getCurrentDate())}]: Run a hard 5k"
}