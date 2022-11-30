package com.bks.springboot.thymeleafdemo.service;

import java.util.List;

import com.bks.springboot.thymeleafdemo.entity.Employee;

public interface EmployeeService {
	public List<Employee> findAll();
	public Employee findById(int id);
	public void save(Employee emp);
	public void delete(int id);
}
