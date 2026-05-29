package com.github.gerdreiss.cruddemo.service

import com.github.gerdreiss.cruddemo.dao.EmployeeDAO
import com.github.gerdreiss.cruddemo.entity.Employee
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EmployeeServiceImpl(val employeeDAO: EmployeeDAO) : EmployeeService {
    override fun findAll(): List<Employee> = employeeDAO.findAll()
    override fun findById(id: Int): Employee? = employeeDAO.findById(id)
    @Transactional
    override fun save(employee: Employee): Employee? = employeeDAO.save(employee)
    @Transactional
    override fun delete(id: Int) = employeeDAO.delete(id)
}