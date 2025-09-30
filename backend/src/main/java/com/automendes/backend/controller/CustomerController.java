package com.automendes.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.automendes.backend.dto.CustomerRequestDTO;
import com.automendes.backend.dto.CustomerResponseDTO;
import com.automendes.backend.entity.Customer;
import com.automendes.backend.mapper.CustomerMapper;
import com.automendes.backend.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {
	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private CustomerService customerService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/register-customer")
	public ResponseEntity<CustomerResponseDTO> registerCustomer(@RequestBody @Valid CustomerRequestDTO dto) {
		Customer customer = customerService.registerCustomer(customerMapper.toCustomer(dto));

		return ResponseEntity.status(HttpStatus.CREATED).body(customerMapper.toCustomerResponseDTO(customer));
	}
}