package com.automendes.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automendes.backend.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, String> {

}
