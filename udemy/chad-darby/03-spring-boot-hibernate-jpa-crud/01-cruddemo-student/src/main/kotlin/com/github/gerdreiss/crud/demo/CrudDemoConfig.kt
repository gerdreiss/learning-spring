package com.github.gerdreiss.crud.demo

import org.springframework.boot.CommandLineRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class CrudDemoConfig {
    @Bean
    fun commandLineRunner(args: Array<String>): CommandLineRunner {
        return CommandLineRunner { _ ->
            println("Hello, World!")
        }
    }
}