package com.company.employee.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.dom4j.Entity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.company.employee.dao.EmployeeDao;
import com.company.employee.model.Employee;

@RestController
@RequestMapping("/company")
public class EmpController {

	@Autowired
	EmployeeDao empDao;
	
	@GetMapping("/emp")
	public ModelAndView list() {
		  ModelAndView model = new ModelAndView("empList");
		  List<Employee> empList = empDao.findAllEmp();
		  model.addObject("empList", empList);
		  
		  return model;
		 }
	@GetMapping("/addEmp")
	 public ModelAndView addEmp() {
	  ModelAndView model = new ModelAndView();	  
	  Employee emp = new Employee();
	  model.addObject("empForm", emp);
	  model.setViewName("empForm");
	  
	  return model;
	 }
	
	@PostMapping("/saveEmp")
	public ModelAndView save(@ModelAttribute("empForm") Employee emp) {
		empDao.saveEmp(emp);
		  
		  return new ModelAndView("redirect:/company/emp");
	}
	/*
	 * public Employee saveEmployee(@Valid @RequestBody Employee emp) { return
	 * empDao.saveEmp(emp); }
	 */
	
	@GetMapping("/getAllEmployee")
	public List<Employee> getAllEmployee() {
		return empDao.findAllEmp();
	}
	
	@GetMapping("/findEmp/{id}")
	public ResponseEntity<Employee> findEmployee(@PathVariable(value="id") Long empId) {
		Employee emp =  empDao.findEmp(empId);
		
		if(emp==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok().body(emp);
	}
	
	@GetMapping("/updateEmp/{id}")
	public ModelAndView updateEmployee(@PathVariable(value="id") Long empId) {
		  ModelAndView model = new ModelAndView();
		  
		  Employee emp =  empDao.findEmp(empId);
		  model.addObject("empForm", emp);
		  model.setViewName("empForm");
		  
		  return model;
		 }
	/*
	 * public ResponseEntity<Employee> updateEmployee(@PathVariable(value="id") Long
	 * empId, @Valid @RequestBody Employee empDetails) { Employee emp =
	 * empDao.findEmp(empId);
	 * 
	 * if(emp==null) { return ResponseEntity.notFound().build(); }
	 * emp.setName(empDetails.getName());
	 * emp.setDesignation(empDetails.getDesignation());
	 * emp.setExperties(empDetails.getExperties());
	 * 
	 * Employee updatedEmp = empDao.saveEmp(emp); return
	 * ResponseEntity.ok().body(updatedEmp); }
	 */
	
	@GetMapping("/removeEmp/{id}")
	public ModelAndView delete(@PathVariable(value="id") Long empId) {
		Employee emp =  empDao.findEmp(empId);
		empDao.removeEmp(emp);
		  
		return new ModelAndView("redirect:/company/emp");
	}

	/*public ResponseEntity<Employee> removeEmployee(@PathVariable(value="id") Long empId) {
		Employee emp =  empDao.findEmp(empId);
		
		if(emp==null) {
			return ResponseEntity.notFound().build();
		}
		empDao.removeEmp(emp);
		return ResponseEntity.ok().build();
	}*/
}

