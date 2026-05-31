package com.github.gerdreiss.cruddemo.service

import com.github.gerdreiss.cruddemo.repository.EmployeeRepository
import com.github.gerdreiss.cruddemo.entity.Employee
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class EmployeeServiceImpl(val employeeRepository: EmployeeRepository) : EmployeeService {
    override fun findAll(): List<Employee> = employeeRepository.findAll()
    override fun findById(id: Int): Employee? = employeeRepository.findByIdOrNull(id)
    @Transactional
    override fun save(employee: Employee): Employee = employeeRepository.save(employee)
    @Transactional
    override fun delete(id: Int): Unit = employeeRepository.deleteById(id)
    override fun exists(id: Int): Boolean = employeeRepository.existsById(id)
}