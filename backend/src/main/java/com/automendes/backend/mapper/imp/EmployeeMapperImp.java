package com.automendes.backend.mapper.imp;

import org.springframework.stereotype.Component;

import com.automendes.backend.dto.EmployeeRequestDTO;
import com.automendes.backend.dto.EmployeeResponseDTO;
import com.automendes.backend.entity.Employee;
import com.automendes.backend.mapper.EmployeeMapper;
import com.fasterxml.uuid.Generators;

@Component
public class EmployeeMapperImp implements EmployeeMapper {

	@Override
	public Employee toEmployee(EmployeeRequestDTO dto) {
		String uuid = Generators.timeBasedEpochRandomGenerator().generate().toString();

		Employee employee = new Employee(uuid, dto.name(), dto.email(), dto.matriculation(), dto.phone(),
				dto.birthDate(), dto.commission(), dto.employeeType(), null);

		return employee;
	}

	@Override
	public EmployeeResponseDTO toEmployeeResponseDTO(Employee entity) {
		EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO(entity.getId(), entity.getName(),
				entity.getEmail(), entity.getMatriculation(), entity.getPhone(), entity.getBirthDate(),
				entity.getCommission(), entity.getEmployeeType());

		return employeeResponseDTO;
	}

}
