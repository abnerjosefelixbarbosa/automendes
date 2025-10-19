package com.automendes.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.automendes.backend.dto.CustomerRequestDTO;
import com.automendes.backend.dto.CustomerResponseDTO;
import com.automendes.backend.entity.Customer;
import com.automendes.backend.mapper.CustomerMapper;
import com.automendes.backend.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/customers")
public class CustomerController {
	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private CustomerService customerService;

	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Registra um cliente."),
		@ApiResponse(responseCode = "400", description = "Retorna um erro de requisição."),
		@ApiResponse(responseCode = "404", description = "Retorna um erro de recurso não encontrado."),
    })
    @Operation(summary = "Registrar cliente.", description = "Registra um cliente.")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/register-customer")
	public ResponseEntity<CustomerResponseDTO> registerCustomer(@RequestBody @Valid CustomerRequestDTO dto) {
		Customer customer = customerService.registerCustomer(customerMapper.toCustomer(dto));

		return ResponseEntity.status(HttpStatus.CREATED).body(customerMapper.toCustomerResponseDTO(customer));
	}

	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Atualiza um cliente."),
		@ApiResponse(responseCode = "400", description = "Retorna um erro de requisição."),
		@ApiResponse(responseCode = "404", description = "Retorna um erro de recurso não encontrado."),
    })
    @Operation(summary = "Atualizar cliente pelo id.", description = "Atualiza um cliente pelo id.")
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/update-customer-by-id")
	public ResponseEntity<CustomerResponseDTO> updateCustomerById(@RequestParam String id,
			@RequestBody @Valid CustomerRequestDTO dto) {
		Customer customer = customerService.updateCustomerById(id, customerMapper.toCustomer(dto));

		return ResponseEntity.status(HttpStatus.OK).body(customerMapper.toCustomerResponseDTO(customer));
	}

	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Busca um cliente."),
		@ApiResponse(responseCode = "400", description = "Retorna um erro de requisição."),
		@ApiResponse(responseCode = "404", description = "Retorna um erro de recurso não encontrado."),
    })
    @Operation(summary = "Buscar cliente pelo documento.", description = "Busca cliente pelo documento.")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/search-customer-by-document")
	public ResponseEntity<CustomerResponseDTO> searchCustomerByDocument(@RequestParam String document) {
		Customer customer = customerService.searchCustomerByDocument(document);

		return ResponseEntity.status(HttpStatus.OK).body(customerMapper.toCustomerResponseDTO(customer));
	}
}