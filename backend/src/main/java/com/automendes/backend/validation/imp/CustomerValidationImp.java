package com.automendes.backend.validation.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.automendes.backend.entity.Customer;
import com.automendes.backend.repository.CustomerRepository;
import com.automendes.backend.validation.CustomerValidation;

@Component
public class CustomerValidationImp implements CustomerValidation {
	@Autowired
	private CustomerRepository customerRepository;
	
	public void validateCustomerRegistration(Customer customer) {
		if (customerRepository.existsByDocumentOrNameOrEmailOrPhone(customer.getDocument(), customer.getName(), customer.getEmail(), customer.getPhone())) {
			throw new RuntimeException("Documento, nome, email ou telehone deve ser único.");
		}
	}
}