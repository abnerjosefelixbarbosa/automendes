package com.automendes.backend.validation;

import com.automendes.backend.entity.Employee;

public interface EmployeeValidation {
	void validateEmployeeRegistration(Employee employee);

	void validateEmployeeUpdate(Employee employee);
}