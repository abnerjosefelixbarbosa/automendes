package com.automendes.backend.service.imp;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.automendes.backend.entity.Customer;
import com.automendes.backend.exception.NotFoundException;
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

	@Transactional
	public Customer updateCustomerById(String id, Customer customer) {
		customerValidation.validateCustomerUpdate(customer);

		Customer customerFound = customerRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Id deve existir."));

		BeanUtils.copyProperties(customer, customerFound, "id");
		
		return customerRepository.save(customerFound);
	}

	public Customer searchCustomerByDocument(String document) {
		return customerRepository.findByDocument(document)
				.orElseThrow(() -> new NotFoundException("Documento deve existir."));
	}
}