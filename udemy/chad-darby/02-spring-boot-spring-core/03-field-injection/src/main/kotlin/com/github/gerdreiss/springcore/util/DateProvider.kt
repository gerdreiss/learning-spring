package com.github.gerdreiss.springcore.util

import org.springframework.stereotype.Component
import java.time.LocalDate

@Component
class DateProvider {
    fun getCurrentDate(): LocalDate = LocalDate.now()
}