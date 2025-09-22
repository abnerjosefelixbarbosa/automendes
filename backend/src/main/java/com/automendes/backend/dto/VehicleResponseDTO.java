package com.automendes.backend.dto;

import java.math.BigDecimal;

import com.automendes.backend.enums.BoxgearType;
import com.automendes.backend.enums.VehicleType;

public record VehicleResponseDTO(
		String id,
		String plate,
		BigDecimal price,
		BoxgearType boxgearType,
		VehicleType vehicleType,
		String modelName
) {}