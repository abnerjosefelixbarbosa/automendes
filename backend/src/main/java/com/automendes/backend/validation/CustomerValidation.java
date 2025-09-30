package com.automendes.backend.validation;

import com.automendes.backend.entity.Customer;

public interface CustomerValidation {
	void validateCustomerRegistration(Customer customer);
}