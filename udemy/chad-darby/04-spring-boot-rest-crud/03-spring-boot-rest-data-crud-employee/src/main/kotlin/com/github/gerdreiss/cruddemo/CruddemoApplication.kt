package com.github.gerdreiss.cruddemo

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CruddemoApplication

fun main(args: Array<String>) {
	runApplication<CruddemoApplication>(*args)
}
