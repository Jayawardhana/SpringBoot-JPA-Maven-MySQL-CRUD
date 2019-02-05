package com.company.employee.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.company.employee.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	
}
