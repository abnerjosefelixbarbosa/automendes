package com.automendes.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automendes.backend.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, String> {

}
