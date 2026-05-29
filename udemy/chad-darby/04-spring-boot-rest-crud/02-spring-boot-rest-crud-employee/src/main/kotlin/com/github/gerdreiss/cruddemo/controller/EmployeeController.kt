package com.github.gerdreiss.cruddemo.controller

import com.github.gerdreiss.cruddemo.dto.EmployeeDTO
import com.github.gerdreiss.cruddemo.entity.Employee
import com.github.gerdreiss.cruddemo.service.EmployeeService
import org.springframework.web.bind.annotation.*

@RestController
class EmployeeController(val employeeService: EmployeeService) {

    @GetMapping("/employees")
    fun findAll(): List<Employee> = employeeService.findAll()

    @GetMapping("/employees/{id}")
    fun findById(@PathVariable id: Int): EmployeeDTO =
        employeeService.findById(id)
            .let { employee ->
                if (employee == null)
                    throw EmployeeNotFoundException("Employee with ID $id not found")
                else
                    EmployeeDTO(employee.firstName, employee.lastName, employee.email)
            }

    @PostMapping("/employees")
    fun create(@RequestBody employee: EmployeeDTO) =
        employeeService.save(
            Employee(
                firstName = employee.firstName,
                lastName = employee.lastName,
                email = employee.email
            )
        )

    @PutMapping("/employees/{id}")
    fun update(@PathVariable id: Int, @RequestBody employee: EmployeeDTO) =
        employeeService.exists(id)
            .let { exists ->
                if (exists)
                    employeeService.save(Employee(id, employee.firstName, employee.lastName, employee.email))
                else
                    throw EmployeeNotFoundException("Employee with ID $id not found")
            }

    @DeleteMapping("/employees/{id}")
    fun delete(@PathVariable id: Int) = employeeService.delete(id)

}