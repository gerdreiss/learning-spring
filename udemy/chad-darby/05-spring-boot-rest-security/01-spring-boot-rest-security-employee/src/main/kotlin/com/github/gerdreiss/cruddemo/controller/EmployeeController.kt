package com.github.gerdreiss.cruddemo.controller

import com.github.gerdreiss.cruddemo.controller.Conversions.toDTO
import com.github.gerdreiss.cruddemo.controller.Conversions.toEntity
import com.github.gerdreiss.cruddemo.dto.EmployeeDTO
import com.github.gerdreiss.cruddemo.service.EmployeeService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import tools.jackson.databind.json.JsonMapper
import java.net.URI

@RestController
class EmployeeController(val employeeService: EmployeeService, val jsonMapper: JsonMapper) {

    @GetMapping("/employees")
    fun findAll(): ResponseEntity<List<EmployeeDTO>> =
        employeeService.findAll()
            .map { it.toDTO() }
            .let { ResponseEntity.ok(it) }

    @GetMapping("/employees/{id}")
    fun findById(@PathVariable id: Int): ResponseEntity<EmployeeDTO> =
        employeeService.findById(id)
            ?.let { ResponseEntity.ok(it.toDTO()) }
            ?: ResponseEntity.notFound().build()

    @PostMapping("/employees")
    fun create(@RequestBody employee: EmployeeDTO): ResponseEntity<EmployeeDTO> =
        employeeService.save(employee.toEntity())
            .let {
                ResponseEntity
                    .created(URI.create("/employees/${it.id}"))
                    .body(it.toDTO())
            }

    @PutMapping("/employees/{id}")
    fun update(@PathVariable id: Int, @RequestBody employee: EmployeeDTO): ResponseEntity<EmployeeDTO> =
        employeeService.findById(id)
            ?.let { employeeService.save(employee.toEntity(id)) }
            ?.let { ResponseEntity.ok(it.toDTO()) }
            ?: ResponseEntity.notFound().build()

    @PatchMapping("/employees/{id}")
    fun patch(@PathVariable id: Int, @RequestBody patch: Map<String, Any>): ResponseEntity<EmployeeDTO> =
        if (patch.containsKey("id")) ResponseEntity.badRequest().build()
        else employeeService.findById(id)
            ?.let { employeeService.save(jsonMapper.updateValue(it, patch)) }
            ?.let { ResponseEntity.ok(it.toDTO()) }
            ?: ResponseEntity.notFound().build()

    @DeleteMapping("/employees/{id}")
    fun delete(@PathVariable id: Int) = employeeService.delete(id)

}