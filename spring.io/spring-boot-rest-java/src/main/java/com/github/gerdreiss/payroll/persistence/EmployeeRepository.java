package com.github.gerdreiss.payroll.persistence;

import com.github.gerdreiss.payroll.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}
