package com.github.gerdreiss.cruddemo.controller

import com.github.gerdreiss.cruddemo.dto.EmployeeDTO
import com.github.gerdreiss.cruddemo.entity.Employee
import com.github.gerdreiss.cruddemo.service.EmployeeService
import org.springframework.web.bind.annotation.*
import tools.jackson.databind.ObjectMapper

@RestController
class EmployeeController(val employeeService: EmployeeService, val jsonMapper: ObjectMapper) {

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

    @PatchMapping("/employees/{id}")
    fun patch(@PathVariable id: Int, @RequestBody patch: Map<String, Any>) =
        employeeService.findById(id)
            .let { employee ->
                if (employee == null)
                    throw EmployeeNotFoundException("Employee with ID $id not found")
                else if (patch.containsKey("id"))
                    throw RuntimeException("Employee id not allowed in patch body")
                else {
                    employeeService.save(jsonMapper.updateValue(employee, patch))
                }
            }

    @DeleteMapping("/employees/{id}")
    fun delete(@PathVariable id: Int) = employeeService.delete(id)

}