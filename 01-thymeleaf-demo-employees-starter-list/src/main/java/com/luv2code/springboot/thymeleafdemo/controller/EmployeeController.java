package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int id, Model theModel){
		// get employee from the service
		Employee theEmployee=employeeService.findById(id);
		//set the employee in the model
		theModel.addAttribute("employee",theEmployee);
		// send over to our form
		return "employees/employee-form";
	}
	@GetMapping("/deleteEmployee")
	public String deleteEmployee(@RequestParam("employeeId") int id){
		employeeService.deleteById(id);
		return "redirect:/employees/list";
	}
}









