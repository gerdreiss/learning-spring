package com.github.gerdreiss.crud.demo.dao

import com.github.gerdreiss.crud.demo.entity.Student

interface StudentDAO {
    fun save(student: Student)
    fun findById(id: Int): Student?
    fun findAll(): List<Student>
    fun findByLastName(name: String): List<Student>
    fun update(student: Student)
    fun delete(student: Student)
    fun deleteAll(): Int
}