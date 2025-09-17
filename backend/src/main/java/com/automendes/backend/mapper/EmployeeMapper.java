package com.automendes.backend.mapper;

import com.automendes.backend.dto.EmployeeRequestDTO;
import com.automendes.backend.dto.EmployeeResponseDTO;
import com.automendes.backend.entity.Employee;

public interface EmployeeMapper {
	Employee toEmployee(EmployeeRequestDTO employeeRequestDTO);
	
	EmployeeResponseDTO toEmployeeResponseDTO(Employee employee);
}