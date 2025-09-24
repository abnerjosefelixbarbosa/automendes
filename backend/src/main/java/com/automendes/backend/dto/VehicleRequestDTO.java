package com.automendes.backend.dto;

import java.math.BigDecimal;

import com.automendes.backend.enums.BoxgearType;
import com.automendes.backend.enums.VehicleType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleRequestDTO {
	private String plate;
	private BigDecimal price;
	private BoxgearType boxgearType;
	private VehicleType vehicleType;
	private String modelName;
}