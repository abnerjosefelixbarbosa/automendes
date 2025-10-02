package com.automendes.backend.validation.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.automendes.backend.entity.Customer;
import com.automendes.backend.repository.CustomerRepository;
import com.automendes.backend.validation.CustomerValidation;

import br.com.caelum.stella.validation.CNPJValidator;
import br.com.caelum.stella.validation.CPFValidator;

@Component
public class CustomerValidationImp implements CustomerValidation {
	@Autowired
	private CustomerRepository customerRepository;
	
	public void validateCustomerRegistration(Customer customer) {
		if (customerRepository.existsByDocumentOrNameOrEmailOrPhone(customer.getDocument(), customer.getName(), customer.getEmail(), customer.getPhone())) {
			throw new RuntimeException("Documento, nome, email ou telefone deve ser único.");
		}
		
		if (customer.getCustomerType().ordinal() == 0) {
			if (!isValidCPF(customer.getDocument())) {
				throw new RuntimeException("Documento cpf deve ser valido.");
			}
		} else {
			if (!isValidCNPJ(customer.getDocument())) {
				throw new RuntimeException("Documento cnpj deve ser valido.");
			}
		}
	}
	
	public void validateCustomerUpdate(Customer customer) {
		if (customerRepository.existsByDocumentOrNameOrEmailOrPhone(customer.getDocument(), customer.getName(), customer.getEmail(), customer.getPhone())) {
			throw new RuntimeException("Documento, nome, email ou telefone deve ser único.");
		}
		
		if (customer.getCustomerType().ordinal() == 0) {
			if (!isValidCPF(customer.getDocument())) {
				throw new RuntimeException("Documento cpf deve ser valido.");
			}
		} else {
			if (!isValidCNPJ(customer.getDocument())) {
				throw new RuntimeException("Documento cnpj deve ser valido.");
			}
		}
	}
	
	private boolean isValidCPF(String cpf) {
		CPFValidator validator = new CPFValidator();
		
		try {
			validator.assertValid(cpf);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	private boolean isValidCNPJ(String cnpj) {
		CNPJValidator validator = new CNPJValidator();
		
		try {
			validator.assertValid(cnpj);
			return true;
		} catch (Exception e) {
			return false;
		}
	}
}