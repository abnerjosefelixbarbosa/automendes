package com.automendes.backend.service.imp;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.automendes.backend.entity.Model;
import com.automendes.backend.entity.Vehicle;
import com.automendes.backend.exception.NotFoundException;
import com.automendes.backend.repository.ModelRepository;
import com.automendes.backend.repository.VehicleRepository;
import com.automendes.backend.service.VehicleService;
import com.automendes.backend.validation.VehicleValidation;
import com.fasterxml.uuid.Generators;

import jakarta.transaction.Transactional;

@Component
public class VehicleServiceImp implements VehicleService {
	@Autowired
	private VehicleRepository vehicleRepository;
	@Autowired
	private ModelRepository modelRepository;
	@Autowired
	private VehicleValidation vehicleValidation;

	@Transactional
	public Vehicle registerVehicle(Vehicle vehicle) {
		vehicleValidation.validateVehicle(vehicle);

		Model model = modelRepository.findByName(vehicle.getModel().getName())
				.orElseThrow(() -> new NotFoundException("Nome do modelo deve ser existente."));

		vehicle.setId(Generators.timeBasedEpochRandomGenerator().generate().toString());

		vehicle.setModel(model);

		return vehicleRepository.save(vehicle);
	}

	@Transactional
	public Vehicle updateVehicleById(String id, Vehicle vehicle) {
		vehicleValidation.validateVehicle(vehicle);

		Model model = modelRepository.findByName(vehicle.getModel().getName())
				.orElseThrow(() -> new NotFoundException("Nome do modelo deve ser existente."));

		vehicle.setModel(model);

		Vehicle vehicleFound = vehicleRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Id deve ser existente."));
		
		BeanUtils.copyProperties(vehicle, vehicleFound, "id");

		return vehicleRepository.save(vehicleFound);
	}

	public Page<Vehicle> listVehicles(Pageable pageable) {
		return vehicleRepository.findAll(pageable);
	}
}