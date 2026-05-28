package com.github.gerdreiss.cruddemo.service

import com.github.gerdreiss.cruddemo.entity.Employee

interface EmployeeService {
    fun findAll(): List<Employee>
    fun findById(id: Int): Employee?
}
