package com.automendes.backend.entity;

import java.math.BigDecimal;
import java.util.List;

import com.automendes.backend.enums.BoxgearType;
import com.automendes.backend.enums.VehicleType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "vehicle_tb")
public class Vehicle {
	@Id
	private String id;
	@Column(nullable = false, unique = true, length = 20)
	private String plate;
	@Column(nullable = false, scale = 2)
	private BigDecimal price;
	@Column(nullable = false)
	private BoxgearType boxgearType;
	@Column(nullable = false)
	private VehicleType vehicleType;
	@ManyToOne
	@JoinColumn(nullable = false, name = "model_id")
	private Model model;
	@OneToMany(mappedBy = "vehicle", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<SaleVehicle> saleVehicles;
}