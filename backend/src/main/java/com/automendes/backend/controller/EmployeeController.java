package com.automendes.backend.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.automendes.backend.dto.EmployeeRequestDTO;
import com.automendes.backend.dto.EmployeeResponseDTO;
import com.automendes.backend.entity.Employee;
import com.automendes.backend.mapper.EmployeeMapper;
import com.automendes.backend.service.EmployeeService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController {
	private EmployeeService employeeService;
	private EmployeeMapper employeeMapper;
	
	public EmployeeController(EmployeeService employeeService, EmployeeMapper employeeMapper) {
		this.employeeService = employeeService;
		this.employeeMapper = employeeMapper;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/register-employee")
	public ResponseEntity<EmployeeResponseDTO> registerEmployee(@Valid @RequestBody EmployeeRequestDTO dto) {
		Employee employee = employeeService.registerEmployee(employeeMapper.toEmployee(dto));

		return ResponseEntity.status(HttpStatus.CREATED).body(employeeMapper.toEmployeeResponseDTO(employee));
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/update-employee-by-id")
	public ResponseEntity<EmployeeResponseDTO> updateEmployeeById(@RequestParam String id,
			@Valid @RequestBody EmployeeRequestDTO dto) {
		Employee employee = employeeService.updateEmployeeById(id, employeeMapper.toEmployee(dto));

		return ResponseEntity.status(HttpStatus.OK).body(employeeMapper.toEmployeeResponseDTO(employee));
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/list-employees")
	public ResponseEntity<List<EmployeeResponseDTO>> listEmployees(Pageable pageable) {
		Page<Employee> page = employeeService.listEmployees(pageable);

		return ResponseEntity.status(HttpStatus.OK).body(page.map(employeeMapper::toEmployeeResponseDTO).toList());
	}
}