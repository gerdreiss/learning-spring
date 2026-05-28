package com.github.gerdreiss.cruddemo.service

import com.github.gerdreiss.cruddemo.dao.EmployeeDAO
import com.github.gerdreiss.cruddemo.entity.Employee
import org.springframework.stereotype.Service

@Service
class EmployeeServiceImpl(val employeeDAO: EmployeeDAO): EmployeeService {
    override fun findAll(): List<Employee> = employeeDAO.findAll()
    override fun findById(id: Int): Employee? = employeeDAO.findById(id)
}