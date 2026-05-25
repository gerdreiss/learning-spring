package com.github.gerdreiss.crud.demo.cli

import com.github.gerdreiss.crud.demo.dao.StudentDAO
import com.github.gerdreiss.crud.demo.entity.Student
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component

@Component
class CrudDemoCommandLineRunner : CommandLineRunner {
    private val studentDAO: StudentDAO

    constructor(studentDAO: StudentDAO) {
        this.studentDAO = studentDAO
    }

    override fun run(vararg args: String) {
        println("Creating new student objects...")

        val student1: Student = Student("Paul", "Doe", "paul@doe.com")
        val student2: Student = Student("Jake", "Foe", "jake@foe.com")
        val student3: Student = Student("John", "Low", "john@low.com")

        println("Saving the students...")
        studentDAO.save(student1)
        studentDAO.save(student2)
        studentDAO.save(student3)

        println("Saved the students. Generated ids: ${student1.id}, ${student2.id}, ${student3.id}")
    }
}