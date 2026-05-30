package com.github.gerdreiss.cruddemo.repository

import com.github.gerdreiss.cruddemo.entity.Employee
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EmployeeRepository : JpaRepository<Employee, Int>