package com.github.gerdreiss.cruddemo.controller

import com.github.gerdreiss.cruddemo.dto.EmployeeDTO
import com.github.gerdreiss.cruddemo.entity.Employee
import com.github.gerdreiss.cruddemo.service.EmployeeService
import org.springframework.web.bind.annotation.*
import tools.jackson.databind.json.JsonMapper

@RestController
class EmployeeController(val employeeService: EmployeeService, val jsonMapper: JsonMapper) {

    @GetMapping("/employees")
    fun findAll(): List<EmployeeDTO> =
        employeeService
            .findAll()
            .map { EmployeeDTO(it.firstName, it.lastName, it.email) }

    @GetMapping("/employees/{id}")
    fun findById(@PathVariable id: Int): EmployeeDTO =
        employeeService
            .findById(id)
            .let { employee ->
                if (employee == null)
                    throw EmployeeNotFoundException("Employee with ID $id not found")
                else
                    EmployeeDTO(employee.firstName, employee.lastName, employee.email)
            }

    @PostMapping("/employees")
    fun create(@RequestBody employee: EmployeeDTO): EmployeeDTO? =
        employeeService
            .save(
                Employee(
                    firstName = employee.firstName,
                    lastName = employee.lastName,
                    email = employee.email
                )
            )
            ?.let { EmployeeDTO(it.firstName, it.lastName, it.email) }

    @PutMapping("/employees/{id}")
    fun update(@PathVariable id: Int, @RequestBody employee: EmployeeDTO): EmployeeDTO? =
        employeeService
            .exists(id)
            .let { exists ->
                if (exists)
                    employeeService
                        .save(Employee(id, employee.firstName, employee.lastName, employee.email))
                        ?.let { EmployeeDTO(it.firstName, it.lastName, it.email) }
                else
                    throw EmployeeNotFoundException("Employee with ID $id not found")
            }

    @PatchMapping("/employees/{id}")
    fun patch(@PathVariable id: Int, @RequestBody patch: Map<String, Any>): EmployeeDTO? =
        employeeService
            .findById(id)
            .let { employee ->
                if (employee == null)
                    throw EmployeeNotFoundException("Employee with ID $id not found")
                else if (patch.containsKey("id"))
                    throw RuntimeException("Employee id not allowed in patch body")
                else {
                    employeeService
                        .save(jsonMapper.updateValue(employee, patch))
                        ?.let { EmployeeDTO(it.firstName, it.lastName, it.email) }
                }
            }

    @DeleteMapping("/employees/{id}")
    fun delete(@PathVariable id: Int) = employeeService.delete(id)

}