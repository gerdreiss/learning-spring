package com.github.gerdreiss.demo.controller

data class StudentErrorResponse(
    val status: Int,
    val message: String,
    val timestamp: Long
)
