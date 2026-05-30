package com.github.gerdreiss.cruddemo.repository

import com.github.gerdreiss.cruddemo.entity.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.rest.core.annotation.RepositoryRestResource

@RepositoryRestResource(path = "employees")
interface EmployeeRepository : JpaRepository<Employee, Int>
