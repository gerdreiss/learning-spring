package com.github.gerdreiss.cruddemo.dao

import com.github.gerdreiss.cruddemo.entity.Employee

interface EmployeeDAO {
    fun findAll(): List<Employee>
    fun findById(id: Int): Employee?
//    fun insert(employee: Employee)
//    fun update(employee: Employee)
//    fun delete(employee: Employee)
}