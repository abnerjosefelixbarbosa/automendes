package com.automendes.backend.mapper;

import com.automendes.backend.dto.VehicleRequestDTO;
import com.automendes.backend.dto.VehicleResponseDTO;
import com.automendes.backend.entity.Vehicle;

public interface VehicleMapper {
	Vehicle toVehicle(VehicleRequestDTO dto);

	VehicleResponseDTO toVehicleResponseDTO(Vehicle entity);
}
