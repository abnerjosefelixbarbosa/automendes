package com.automendes.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.automendes.backend.entity.Vehicle;

public interface VehicleService {
	Vehicle registerVehicle(Vehicle vehicle);

	Page<Vehicle> listVehicles(Pageable pageable);
}