package com.automendes.backend.entity;

import java.util.List;

import com.automendes.backend.enums.CustomerType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "customer_tb")
public class Customer {
	@Id
	private String id;
	@Column(nullable = false, unique = true, length = 14)
	private String document;
	@Column(nullable = false, unique = true, length = 100)
	private String name;
	@Column(nullable = false, unique = true, length = 50)
	private String email;
	@Column(nullable = false, unique = true, length = 30)
	private String phone;
	@Column(nullable = false)
	private CustomerType customerType;
	@OneToMany(mappedBy = "customer", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private List<Sale> sales;
}