package com.github.gerdreiss.crud.demo.dao

import com.github.gerdreiss.crud.demo.entity.Student

interface StudentDAO {
    fun save(student: Student)
    fun findById(id: Int): Student
}