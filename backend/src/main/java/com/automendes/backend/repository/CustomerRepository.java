package com.automendes.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automendes.backend.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {
	boolean existsByDocumentOrNameOrEmailOrPhone(String document, String name, String email, String phone);

	Optional<Customer> findByDocument(String document);
}