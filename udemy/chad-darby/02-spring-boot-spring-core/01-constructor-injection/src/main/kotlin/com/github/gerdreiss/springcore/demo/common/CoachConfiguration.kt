package com.github.gerdreiss.springcore.demo.common

import com.github.gerdreiss.springcore.util.DateProvider
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CoachConfiguration {
    @Bean
    fun swimCoach(): Coach = SwimCoach(DateProvider())
}