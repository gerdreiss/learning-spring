package com.github.gerdreiss.springcore.demo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class SpringCoreDemoApplication

fun main(args: Array<String>) {
	runApplication<SpringCoreDemoApplication>(*args)
}
