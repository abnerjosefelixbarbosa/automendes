package com.automendes.backend.mapper.imp;

import org.springframework.stereotype.Component;

import com.automendes.backend.dto.CustomerRequestDTO;
import com.automendes.backend.dto.CustomerResponseDTO;
import com.automendes.backend.entity.Customer;
import com.automendes.backend.mapper.CustomerMapper;

@Component
public class CustomerMapperImp implements CustomerMapper {
	public Customer toCustomer(CustomerRequestDTO dto) {
		Customer customer = new Customer(null, dto.getDocument(), dto.getName(), dto.getEmail(), dto.getPhone(),
				dto.getCustomerType(), null);
		
		return customer;
	}

	public CustomerResponseDTO toCustomerResponseDTO(Customer entity) {
		CustomerResponseDTO customerResponseDTO = new CustomerResponseDTO(entity.getId(), entity.getDocument(), entity.getName(), entity.getEmail(),
				entity.getPhone(), entity.getCustomerType());
		
		return customerResponseDTO;
	}
}