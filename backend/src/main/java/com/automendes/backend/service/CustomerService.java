package com.automendes.backend.service;

import com.automendes.backend.entity.Customer;

public interface CustomerService {
	Customer registerCustomer(Customer customer);
	
	Customer updateCustomerById(String id, Customer customer);
}