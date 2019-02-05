package com.company.employee.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.company.employee.model.Employee;
import com.company.employee.repo.EmployeeRepository;

@Service
public class EmployeeDao {

	@Autowired
	EmployeeRepository empRepo;
	
	//to save an employee
	public Employee saveEmp(Employee emp) {
		return empRepo.save(emp);
	}
	
	//to remove an employee
	public void removeEmp(Employee emp) {
		empRepo.delete(emp);
	}
	
	//to search all employee
	public List<Employee> findAllEmp(){
		return empRepo.findAll();
	}
	
	//to serach employee by id
	public Employee findEmp(Long empId){
		return empRepo.findById(empId).orElse(null);
	}
	
	//to update employee by id
	public Employee updateEmp(Long empId){
		return empRepo.findById(empId).orElse(null);
	}
}
