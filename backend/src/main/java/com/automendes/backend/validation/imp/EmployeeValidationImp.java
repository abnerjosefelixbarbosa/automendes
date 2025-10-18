package com.automendes.backend.validation.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.automendes.backend.entity.Employee;
import com.automendes.backend.repository.EmployeeRepository;
import com.automendes.backend.validation.EmployeeValidation;

@Component
public class EmployeeValidationImp implements EmployeeValidation {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public void validateEmployee(Employee employee) {
		if (employeeRepository.existsByNameOrEmailOrMatriculationOrPhone(employee.getName(), employee.getEmail(), employee.getMatriculation(), employee.getPhone())) {
			throw new RuntimeException("Nome, email, matrícula e telefone deve ser únicos.");
		}
		
		if (employee.getEmployeeType().ordinal() == 2) {
			if (employee.getCommission() == null) {
				throw new RuntimeException("Comissão não deve ser nulo.");
			}
			
			if (employee.getCommission().scale() != 2) {
				throw new RuntimeException("Comissão deve ter 2 dígitos depois da virgula.");
			}
			
			if (employee.getCommission().toString().equals("0.00")) {
				throw new RuntimeException("Comissão não deve ser 0.");
			}
		}
	}
}