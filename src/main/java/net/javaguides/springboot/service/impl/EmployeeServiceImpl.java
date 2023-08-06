package net.javaguides.springboot.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.stereotype.Service;

import net.javaguides.springboot.exception.ResourceNotFoundException;
import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.repository.EmployeeRepository;
import net.javaguides.springboot.service.EmployeeService;

@Service
public class EmployeeServiceImpl implements EmployeeService{

	private EmployeeRepository employeeRepository;
	

	public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
		super();
		this.employeeRepository = employeeRepository;
	}

	@Override
	public Employee saveEmployee(Employee employee) {
		// TODO Auto-generated method stub
		return employeeRepository.save(employee);
	}

	@Override
	public List<Employee> getAllEmployees() {
		// TODO Auto-generated method stub
		return employeeRepository.findAll();
	}

	@Override
	public Employee getEmployeeById(long id) {
//		Optional<Employee> employee = employeeRepository.findById(id);
//		if(employee.isPresent()) {
//			return employee.get();
//		}else {
//			throw new ResourceNotFoundException("Employee", "ID", id);
//		}
			
		return employeeRepository.findById(id).orElseThrow(() -> 
			   new ResourceNotFoundException("Employee", "Id", id)
		);
	}



	@Override
	public Employee upDateEmployee(Employee employee,Long id) {
		// TODO Auto-generated method stub
		
		Employee existingEmployee = employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Employee", "Id", id));
		
		existingEmployee.setFirstName(employee.getFirstName());
		existingEmployee.setLastName(employee.getLastName());
		existingEmployee.setEmail(employee.getEmail());
		employeeRepository.save(existingEmployee);
		
		return existingEmployee;
	}

	@Override
	public Employee deleteById(Long id) {
		// TODO Auto-generated method stub
		
		// check whether a employee exist in a DB or not
		Employee employee = employeeRepository.findById(id).orElseThrow(()-> new  ResourceNotFoundException("Employee", "Id", id));
		
		employeeRepository.deleteById(id);
		
		return employee;
	}

}
