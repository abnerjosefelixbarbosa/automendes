package com.automendes.backend.mapper.imp;

import org.springframework.stereotype.Component;

import com.automendes.backend.dto.VehicleRequestDTO;
import com.automendes.backend.dto.VehicleResponseDTO;
import com.automendes.backend.entity.Model;
import com.automendes.backend.entity.Vehicle;
import com.automendes.backend.mapper.VehicleMapper;

@Component
public class VehicleMapperImp implements VehicleMapper {
	public Vehicle toVehicle(VehicleRequestDTO dto) {
		Model model = new Model(null, dto.getModelName(), null, null);

		Vehicle vehicle = new Vehicle(null, dto.getPlate(), dto.getPrice(), dto.getBoxgearType(), dto.getVehicleType(),
				model, null);

		return vehicle;
	}

	public VehicleResponseDTO toVehicleResponseDTO(Vehicle entity) {
		VehicleResponseDTO vehicleResponseDTO = new VehicleResponseDTO(entity.getId(), entity.getPlate(),
				entity.getPrice(), entity.getBoxgearType(), entity.getVehicleType(), entity.getModel().getName());

		return vehicleResponseDTO;
	}
}