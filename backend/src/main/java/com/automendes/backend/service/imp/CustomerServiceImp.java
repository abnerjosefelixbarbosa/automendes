package com.automendes.backend.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.automendes.backend.entity.Customer;
import com.automendes.backend.repository.CustomerRepository;
import com.automendes.backend.service.CustomerService;
import com.automendes.backend.validation.CustomerValidation;
import com.fasterxml.uuid.Generators;

import jakarta.transaction.Transactional;

@Service
public class CustomerServiceImp implements CustomerService {
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private CustomerValidation customerValidation;

	@Transactional
	public Customer registerCustomer(Customer customer) {
		customerValidation.validateCustomerRegistration(customer);
		
		customer.setId(Generators.timeBasedEpochRandomGenerator().generate().toString());
		
		return customerRepository.save(customer);
	}
}