package net.javaguides.springboot.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.persistence.PostUpdate;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.service.EmployeeService;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService employeeService) {
		super();
		this.employeeService = employeeService;
	}
	
	//build create employee REST API
	@PostMapping()
	public ResponseEntity<Employee> saveEmployee(@RequestBody Employee employee){
		return new ResponseEntity<Employee>(employeeService.saveEmployee(employee),HttpStatus.CREATED);
		
	}
	
	//build get all employees REST API
	@GetMapping
	public List<Employee> getAllEmployees()
	{
		return employeeService.getAllEmployees();
	}
	
	// build get employee by id REST API
	// http://localhost:8080/api/employees/1
	@GetMapping("/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable("id") long employeeId)
	{
		return new ResponseEntity<>(employeeService.getEmployeeById(employeeId),HttpStatus.OK) ;
	}
	
	// build  employee by id REST API
	// http://localhost:8080/api/employees/1	
	@PutMapping("/{id}")
	public ResponseEntity<Employee> upDateEmployee(@RequestBody Employee employee,@PathVariable("id") long employeeId)
	{	
		return new ResponseEntity<Employee>(employeeService.upDateEmployee(employee,employeeId),HttpStatus.OK);
		
	}
	
	//build delete employee REST API
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long employeeId)
	{ 
		
		// delete employee from DB
	Employee employee =	employeeService.deleteById(employeeId);
	
		return new ResponseEntity<String>(employee.getFirstName() + " is deleted from DataBase",HttpStatus.OK);
	}

}


