package com.automendes.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.automendes.backend.entity.Employee;

public interface EmployeeService {
	Employee registerEmployee(Employee employee);

	Employee updateEmployee(String id, Employee employee);

	Page<Employee> listEmployees(Pageable pageable);
}