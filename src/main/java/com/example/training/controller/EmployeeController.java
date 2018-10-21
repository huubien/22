package com.example.training.controller;



import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.training.mapper.impl.EmployeeServiceImpl;
import com.example.training.model.Employees;


@Controller
public class EmployeeController {

	public static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	EmployeeServiceImpl employeeservice;
	

	@RequestMapping(value = { "/", "/index" })
	public String getIndex() {
		return "form";
	}
	
	@RequestMapping(value="/employees", method = RequestMethod.POST)
	public String create(Model model, @Valid @ModelAttribute("employee") Employees emp) {
		this.employeeservice.save(emp);
		model.addAttribute("employee", emp);
		return "redirect:/employees";
	}
	
	
	
	/*
	 * Delete 
	 */
	@RequestMapping("/employees/{id}/delete")
	public String deletePost(@PathVariable("id") Long id) {
		logger.info("Deleting employees id:" + id);
		this.employeeservice.delete(id);;
		return "redirect:/employees";
	}
	
	/*
	 * Update 
	 */
	@RequestMapping(value = "/employees/update", method = RequestMethod.PUT)
	public String save(@Valid Employees emp, BindingResult result, RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return "form";
		}
		employeeservice.update(emp);
		redirect.addFlashAttribute("success", "Saved employee successfully!");
		return "redirect:/employees";
	}
	/*
	 * List all employee
	 */
	@RequestMapping(value = "/employees", method = RequestMethod.GET)
	public String listAllPosts(Model model) {
		logger.info("Litsing all employees...");
		
		model.addAttribute("employees", employeeservice.findAll());
		return "employee-index";
	}
	
	/*
	 * Display employee details
	 */
	@RequestMapping("/employees/{id}/edit")
	public String edit(@PathVariable Long id, Model model) {
		model.addAttribute("employee", employeeservice.find(id));
		return "form";
	}
	

	@RequestMapping("/employee/search{id}")
	public String search(@RequestParam("id") Long id, Model model) {
		if (id.equals("")) {
			return "redirect:/employee";
		}

		model.addAttribute("employees", employeeservice.find(id));
		return "employee-index";
	}
	}
