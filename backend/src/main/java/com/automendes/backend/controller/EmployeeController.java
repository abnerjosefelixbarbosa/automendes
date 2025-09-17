package com.automendes.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping(name = "/employees")
public class EmployeeController {
	@Autowired
    private EmployeeService employeeService;
	@Autowired
	private EmployeeMapper employeeMapper;
	
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(name = "/register-employee")
	public ResponseEntity<EmployeeResponseDTO> registerEmployee(@Valid @RequestBody EmployeeRequestDTO employeeRequestDTO) {
		Employee employee = employeeMapper.toEmployee(employeeRequestDTO);
		
		System.out.println(employee.toString());
		
		//Employee employeeRegisted = employeeService.registerEmployee(employee);

		//EmployeeResponseDTO employeeResponseDTO = employeeMapper.toEmployeeResponseDTO(employeeRegisted);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping(name = "/update-employee")
	public ResponseEntity<Employee> updateEmployee(@RequestParam String id, @RequestBody Employee employee) {

		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}

	@ResponseStatus(HttpStatus.OK)
	@GetMapping(name = "/list-employees")
	public ResponseEntity<Page<Employee>> listEmployees(Pageable pageable) {
		return ResponseEntity.status(HttpStatus.CREATED).body(null);
	}
	
}