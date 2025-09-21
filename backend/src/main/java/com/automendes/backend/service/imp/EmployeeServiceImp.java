package com.automendes.backend.service.imp;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.automendes.backend.entity.Employee;
import com.automendes.backend.exception.NotFoundException;
import com.automendes.backend.repository.EmployeeRepository;
import com.automendes.backend.service.EmployeeService;
import com.automendes.backend.validation.EmployeeValidation;
import com.fasterxml.uuid.Generators;

import jakarta.transaction.Transactional;

@Service
public class EmployeeServiceImp implements EmployeeService {
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private EmployeeValidation employeeValidation;

	@Transactional
	public Employee registerEmployee(Employee employee) {
		employeeValidation.validateEmployeeRegistration(employee);
		
		String uuid = Generators.timeBasedEpochRandomGenerator().generate().toString();
		
		employee.setId(uuid);

		return employeeRepository.save(employee);
	}

	@Transactional
	public Employee updateEmployeeById(String id, Employee employee) {
		employeeValidation.validateEmployeeUpdate(employee);
		
		Employee employeeFound = employeeRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Funcionário deve ser existente."));
		
		BeanUtils.copyProperties(employee, employeeFound, "id");

		return employeeRepository.save(employeeFound);
	}

	public Page<Employee> listEmployees(Pageable pageable) {
		return employeeRepository.findAll(pageable);
	}
}