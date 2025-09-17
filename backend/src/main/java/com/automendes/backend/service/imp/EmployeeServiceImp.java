package com.automendes.backend.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.automendes.backend.entity.Employee;
import com.automendes.backend.repository.EmployeeRepository;
import com.automendes.backend.service.EmployeeService;
import com.automendes.backend.validation.EmployeeValidation;

@Service
public class EmployeeServiceImp implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private EmployeeValidation employeeValidation;

	@Override
	public Employee registerEmployee(Employee employee) {
		employeeValidation.validateEmployeeRegistration(employee);

		return employeeRepository.save(employee);
	}

	@Override
	public Employee updateEmployee(String id, Employee employee) {
		employeeValidation.validateEmployeeUpdate(employee);

		return employeeRepository.save(employee);
	}

	@Override
	public Page<Employee> listEmployees(Pageable pageable) {
		return employeeRepository.findAll(pageable);
	}
	
}