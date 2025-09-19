package com.automendes.backend.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import com.automendes.backend.enums.EmployeeType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "employee_tb")
public class Employee {
	@Id
	private String id;
	@Column(nullable = false, unique = true, length = 100)
	private String name;
	@Column(nullable = false, unique = true, length = 50)
	private String email;
	@Column(nullable = false, unique = true, length = 10)
	private String matriculation;
	@Column(nullable = false, unique = true, length = 30)
	private String phone;
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private LocalDate birthDate;
	@Column(scale = 2)
	private BigDecimal commission;
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private EmployeeType employeeType;
	@OneToMany(mappedBy = "employee", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Sale> sales;
}