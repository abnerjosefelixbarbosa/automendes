package com.automendes.backend.validation.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.automendes.backend.entity.Vehicle;
import com.automendes.backend.repository.VehicleRepository;
import com.automendes.backend.validation.VehicleValidation;

@Component
public class VehicleValidationImp implements VehicleValidation {
	@Autowired
	private VehicleRepository vehicleRepository;
	
	public void validateVehicle(Vehicle vehicle) {
		if (vehicle.getPrice().scale() != 2) {
			throw new RuntimeException("Preço deve ter 2 digitos de precisão.");
		}
		
		if (vehicle.getPrice().toString().equals("0.00")) {
			throw new RuntimeException("Preço não deve ser 0.");
		}
		
		if (!vehicle.getPlate().equals(null) && !vehicle.getPlate().isEmpty()) {
			if (vehicleRepository.existsByPlate(vehicle.getPlate())) {
				throw new RuntimeException("Placa não deve ser repetida.");
			}
		}
	}
}