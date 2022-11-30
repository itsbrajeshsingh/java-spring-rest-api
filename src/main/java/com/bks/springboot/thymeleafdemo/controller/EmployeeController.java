package com.bks.springboot.thymeleafdemo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bks.springboot.thymeleafdemo.entity.Employee;
import com.bks.springboot.thymeleafdemo.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	EmployeeService empservice;
	public EmployeeController(EmployeeService emp) {
		this.empservice = emp;
	}
	
	@GetMapping("/list")
	public String getList(Model theModel) {
		theModel.addAttribute("theDate",new java.util.Date());
		theModel.addAttribute("employees",empservice.findAll());
		return "employees/list-employees";
	}
	
	@GetMapping("/showFormForAdd")
	public String showForm(Model theModel) {
		Employee employee = new Employee();
		theModel.addAttribute("theDate", new java.util.Date());
		theModel.addAttribute("employee", employee);
		return "employees/employee-form";
	}
	
	@PostMapping("/save")
	public String saveEmp(@ModelAttribute("employee") Employee emp) {
		empservice.save(emp);
		return "redirect:/employees/list";
	}
	
	@GetMapping("/showFormForUpdate")
	public String showUpdateform(@RequestParam("empId") int id, Model theModel) {
		Employee employee = empservice.findById(id);
		theModel.addAttribute("employee",employee);
		return "employees/employee-form";
	}
	
	@GetMapping("/delete")
	public String deleteEmp(@RequestParam("empId") int id) {
		empservice.delete(id);
		return "redirect:/employees/list";
	}
}
