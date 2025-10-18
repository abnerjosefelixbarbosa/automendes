package com.automendes.backend.mapper;

import com.automendes.backend.dto.CustomerRequestDTO;
import com.automendes.backend.dto.CustomerResponseDTO;
import com.automendes.backend.entity.Customer;

public interface CustomerMapper {
    Customer toCustomer(CustomerRequestDTO dto);
	
	CustomerResponseDTO toCustomerResponseDTO(Customer entity);
}