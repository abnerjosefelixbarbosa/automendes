package com.automendes.backend.validation;

import com.automendes.backend.entity.Vehicle;

public interface VehicleValidation {
	void validateVehicleRegistration(Vehicle vehicle);

	void validateVehicleUpdate(Vehicle vehicle);
}
