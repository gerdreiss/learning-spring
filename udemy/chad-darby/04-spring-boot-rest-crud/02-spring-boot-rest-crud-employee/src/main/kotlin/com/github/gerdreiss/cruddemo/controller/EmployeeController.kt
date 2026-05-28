package com.github.gerdreiss.cruddemo.controller

import com.github.gerdreiss.cruddemo.entity.Employee
import com.github.gerdreiss.cruddemo.service.EmployeeService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class EmployeeController(val employeeService: EmployeeService) {

    @GetMapping("/employees")
    fun findAll(): List<Employee> = employeeService.findAll()

    @GetMapping("/employees/{id}")
    fun findById(@PathVariable id: Int): Employee? = employeeService.findById(id)

}