package com.github.gerdreiss.demo.controller

data class StudentNotFoundException(
    override val message: String,
) : RuntimeException(message)
