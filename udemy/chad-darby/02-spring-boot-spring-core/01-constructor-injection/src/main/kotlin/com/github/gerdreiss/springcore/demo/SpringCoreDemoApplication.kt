package com.github.gerdreiss.springcore.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication(
    scanBasePackages = [
        "com.github.gerdreiss.springcore.util",
        "com.github.gerdreiss.springcore.demo"
    ],
)
class SpringCoreDemoApplication

fun main(args: Array<String>) {
    runApplication<SpringCoreDemoApplication>(*args)
}
