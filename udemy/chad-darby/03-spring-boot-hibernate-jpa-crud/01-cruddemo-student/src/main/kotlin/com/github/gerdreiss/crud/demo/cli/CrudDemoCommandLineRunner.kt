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
        val created = createStudentList()
        readStudentById(created.random().id!!)
        readAllStudents()
        readStudentByLastName(created.random().lastName)
        updateStudent()
        removeStudent()
        deleteAll()
    }

    private fun createStudentList(): List<Student> {
        println("Creating new student objects...")
        val student1 = Student("Paul", "Doe", "paul@doe.com")
        val student2 = Student("Jake", "Foe", "jake@foe.com")
        val student3 = Student("John", "Low", "john@low.com")

        println("Saving the students...")
        studentDAO.save(student1)
        studentDAO.save(student2)
        studentDAO.save(student3)

        println("Saved the students. Generated ids: ${student1.id}, ${student2.id}, ${student3.id}")

        return listOf(student1, student2, student3)
    }

    private fun readStudentById(id: Int) {
        this.studentDAO.findById(id)?.let { println("Found student: $it") }
    }

    private fun readAllStudents() {
        this.studentDAO.findAll().forEach { println(it) }
    }

    private fun readStudentByLastName(lastName: String) {
        this.studentDAO.findByLastName(lastName).forEach { println("Found student: $it") }
    }

    private fun updateStudent() {
        this.studentDAO.findAll()
            .randomOrNull()
            ?.copy(lastName = "Random")
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