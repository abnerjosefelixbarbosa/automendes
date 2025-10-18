package com.automendes.backend.mapper.imp;

import org.springframework.stereotype.Component;

import com.automendes.backend.dto.EmployeeRequestDTO;
import com.automendes.backend.dto.EmployeeResponseDTO;
import com.automendes.backend.entity.Employee;
import com.automendes.backend.mapper.EmployeeMapper;

@Component
public class EmployeeMapperImp implements EmployeeMapper {
	public Employee toEmployee(EmployeeRequestDTO dto) {
		Employee employee = new Employee(null, dto.getName(), dto.getEmail(), dto.getMatriculation(), dto.getPhone(),
				dto.getBirthDate(), dto.getCommission(), dto.getEmployeeType(), null);

		if (dto.getEmployeeType().ordinal() != 2) {
			employee.setCommission(null);
		}

		return employee;
	}

	public EmployeeResponseDTO toEmployeeResponseDTO(Employee entity) {
		EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO(entity.getId(), entity.getName(), entity.getEmail(),
				entity.getMatriculation(), entity.getPhone(), entity.getBirthDate(), entity.getCommission(),
				entity.getEmployeeType());

		return employeeResponseDTO;
	}
}