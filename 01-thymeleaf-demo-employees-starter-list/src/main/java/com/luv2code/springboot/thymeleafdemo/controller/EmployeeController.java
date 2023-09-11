package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model theModel) {
		List<Employee> theEmployees=employeeService.findAll();

		// add to the spring model
		theModel.addAttribute("employees", theEmployees);

		return "employees/list-employees";
	}

	@GetMapping("showFormForAdd")
	public String showFormForAdd(Model theModel){
		// create model attribute to bind from data
		Employee theEmployee=new Employee();
		theModel.addAttribute("employee",theEmployee);

		//dosya yolu
		return "employees/employee-form";
	}

	@PostMapping("/save") //modelAttribute te form kısmında th:object te yazdıgımızın aynısı yazıyoruz
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee){

		//save th employee
		employeeService.save(theEmployee);

		// use a redirect to prevent duplicate submissions =>request mapping
		return "redirect:/employees/list";

	}
}









