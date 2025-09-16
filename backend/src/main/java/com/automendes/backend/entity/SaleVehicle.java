package com.automendes.backend.entity;

import com.automendes.backend.enums.PaymentType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@IdClass(SaleVehicleId.class)
@Table(name = "sale_vehicle_tb")
public class SaleVehicle {
	@Column(nullable = false)
	private PaymentType paymentType;
	@Id
	@ManyToOne
	@JoinColumn(name = "sale_id")
	private Sale sale;
	@Id
	@ManyToOne
	@JoinColumn(name = "vehicle_id")
	private Vehicle vehicle;
}