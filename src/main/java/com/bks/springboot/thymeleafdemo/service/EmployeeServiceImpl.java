package com.bks.springboot.thymeleafdemo.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bks.springboot.thymeleafdemo.dao.EmployeeRepository;
import com.bks.springboot.thymeleafdemo.entity.Employee;


@Service
public class EmployeeServiceImpl implements EmployeeService {
	private EmployeeRepository employeerepo;
	
	@Autowired
	public EmployeeServiceImpl(EmployeeRepository empd) {
		this.employeerepo=empd;
	}	
	@Override
	public List<Employee> findAll() {
		return employeerepo.findAllByOrderByLastNameAsc();
	}

	@Override
	public Employee findById(int id) {
		Optional<Employee> result = employeerepo.findById(id);
		Employee emp = null;
		if(result.isPresent()) emp=result.get();
		else throw new RuntimeException("Employee with id: "+id+" not found.");
		return emp;
	}

	@Override
	@Transactional
	public void save(Employee emp) {
		employeerepo.save(emp);
	}

	@Override
	public void delete(int id) {
		Optional<Employee> result = employeerepo.findById(id);
		if(result.isPresent()) employeerepo.deleteById(id);
		else throw new RuntimeException("Employee with id: "+id+" not found.");
	}

}
