package com.github.gerdreiss.demo.controller

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler

@ControllerAdvice
class StudentRestExceptionHandler {

    @ExceptionHandler
    fun handleStudentNotFoundException(ex: StudentNotFoundException): ResponseEntity<StudentErrorResponse> {
        val errorResponse =
            StudentErrorResponse(HttpStatus.NOT_FOUND.value(), ex.message, System.currentTimeMillis())
        return ResponseEntity(errorResponse, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler
    fun handleException(ex: Exception): ResponseEntity<StudentErrorResponse> {
        val errorResponse =
            StudentErrorResponse(HttpStatus.BAD_REQUEST.value(), ex.message.orEmpty(), System.currentTimeMillis())
        return ResponseEntity(errorResponse, HttpStatus.BAD_REQUEST)
    }

}