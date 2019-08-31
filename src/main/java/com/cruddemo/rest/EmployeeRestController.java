package com.cruddemo.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cruddemo.dao.EmployeeDAO;
import com.cruddemo.entity.Employee;

@RestController
@RequestMapping("/api")
public class EmployeeRestController {

	private EmployeeDAO employeeDAO;
	@Autowired
	public EmployeeRestController(EmployeeDAO theEmployeeDAO) {
		employeeDAO = theEmployeeDAO;
	}
	
	// expose "/employees" and return list of employees
	@GetMapping("/employees")
	public List<Employee> findAll() {
		return employeeDAO.findAll();
	}

	@GetMapping("/employees/{userId}/{bill}/{groceries}")
	public String getEmployee(@PathVariable int userId, @PathVariable int bill, @PathVariable int groceries) {
		return employeeDAO.findById(userId, bill, groceries);
	}
}










