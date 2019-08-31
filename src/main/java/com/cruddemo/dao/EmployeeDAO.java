package com.cruddemo.dao;

import java.util.List;

import com.cruddemo.entity.Employee;

public interface EmployeeDAO {

	public List<Employee> findAll();

	public String findById(int id, int bill, int groceries);
}
