package com.github.gerdreiss.demo.controller

import com.github.gerdreiss.demo.entity.Student
import jakarta.annotation.PostConstruct
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class StudentRestController {
    lateinit var students: List<Student>

    @PostConstruct
    fun init() {
        students = listOf(
            Student("John", "Doe"),
            Student("Jane", "Doe"),
            Student("Jack", "Doe"),
            Student("James", "Doe"),
        )
    }

    @GetMapping("/students")
    fun students(): List<Student> = students

    @GetMapping("/students/{studentId}")
    fun student(@PathVariable("studentId") studentId: Int): Student {
        if (studentId >= students.size) {
            throw StudentNotFoundException("Student with id $studentId not found")
        }
        return students[studentId]
    }


}