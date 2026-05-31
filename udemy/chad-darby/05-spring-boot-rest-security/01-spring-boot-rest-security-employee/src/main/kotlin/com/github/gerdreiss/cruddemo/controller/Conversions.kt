package com.github.gerdreiss.cruddemo.controller

import com.github.gerdreiss.cruddemo.dto.EmployeeDTO
import com.github.gerdreiss.cruddemo.entity.Employee

object Conversions {
    fun Employee.toDTO() = EmployeeDTO(firstName, lastName, email)
    fun EmployeeDTO.toEntity(id: Int = 0) = Employee(id, firstName, lastName, email)
}