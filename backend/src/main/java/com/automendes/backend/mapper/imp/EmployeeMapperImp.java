package com.automendes.backend.mapper.imp;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.automendes.backend.dto.EmployeeRequestDTO;
import com.automendes.backend.dto.EmployeeResponseDTO;
import com.automendes.backend.entity.Employee;
import com.automendes.backend.mapper.EmployeeMapper;

@Component
public class EmployeeMapperImp implements EmployeeMapper {

	@Override
	public Employee toEmployee(EmployeeRequestDTO employeeRequestDTO) {
		Employee employee = new Employee(null, null, null, null, null, null, null, null, null);

		BeanUtils.copyProperties(employeeRequestDTO, employee);

		return employee;
	}

	@Override
	public EmployeeResponseDTO toEmployeeResponseDTO(Employee employee) {
		EmployeeResponseDTO employeeResponseDTO = new EmployeeResponseDTO(null, null, null, null, null, null, null,
				null);

		BeanUtils.copyProperties(employee, employeeResponseDTO);

		return employeeResponseDTO;
	}

}
