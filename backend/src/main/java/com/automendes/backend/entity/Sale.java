package com.automendes.backend.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import com.automendes.backend.enums.PaymentType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
@Table(name = "sale_tb")
public class Sale {
	@Id
	private String id;
	@Column(nullable = false)
	private Integer quantity;
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = false)
	private LocalDateTime saleDateTime;
	@Column(nullable = false, scale = 2)
	private BigDecimal total;
	@Column(nullable = false)
	private PaymentType paymentType;
	@ManyToOne
	@JoinColumn(nullable = false, name = "customer_id")
	private Customer customer;
	@ManyToOne
	@JoinColumn(nullable = false, name = "employee_id")
	private Employee employee;
	@OneToMany(mappedBy = "sale", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private List<SaleVehicle> saleVehicles;
}