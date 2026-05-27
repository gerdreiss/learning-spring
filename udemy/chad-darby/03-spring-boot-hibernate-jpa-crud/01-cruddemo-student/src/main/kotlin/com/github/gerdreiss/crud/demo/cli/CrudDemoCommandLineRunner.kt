package com.github.gerdreiss.crud.demo.cli

import com.github.gerdreiss.crud.demo.dao.StudentDAO
import com.github.gerdreiss.crud.demo.entity.Student
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import kotlin.time.Duration.Companion.seconds

@Component
class CrudDemoCommandLineRunner : CommandLineRunner {
    private val studentDAO: StudentDAO

    constructor(studentDAO: StudentDAO) {
        this.studentDAO = studentDAO
    }

    override fun run(vararg args: String) {
        createStudentList()
        readStudentById()
        readAllStudents()
        readStudentByLastName()
        updateStudent()
        removeStudent()
        deleteAll()
    }

    private fun createStudentList() {
        println("Creating new student objects...")
        val student1 = Student("Paul", "Doe", "paul@doe.com")
        val student2 = Student("Jake", "Foe", "jake@foe.com")
        val student3 = Student("John", "Low", "john@low.com")

        println("Saving the students...")
        studentDAO.save(student1)
        studentDAO.save(student2)
        studentDAO.save(student3)

        println("Saved the students. Generated ids: ${student1.id}, ${student2.id}, ${student3.id}")
    }

    private fun readStudentById() {
        this.studentDAO.findById(10)?.let { println("Found student: $it") }
    }

    private fun readAllStudents() {
        this.studentDAO.findAll().forEach { println(it) }
    }

    private fun readStudentByLastName() {
        this.studentDAO.findByLastName("Doe").forEach { println("Found student: $it") }
    }

    private fun updateStudent() {
        this.studentDAO.findAll()
            .firstOrNull()
            ?.copy(lastName = "Boe")
            ?.let {
                this.studentDAO.update(it)
                println("Updated student: $it")
            }
    }

    private fun removeStudent() {
        this.studentDAO.findAll()
            .firstOrNull()
            ?.let {
                this.studentDAO.delete(it)
                println("Deleted student: $it")
            }
    }

    private fun deleteAll() {
        this.studentDAO.deleteAll().let { println("Deleted $it students") }
    }
}