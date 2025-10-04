package com.automendes.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.automendes.backend.dto.VehicleRequestDTO;
import com.automendes.backend.dto.VehicleResponseDTO;
import com.automendes.backend.entity.Vehicle;
import com.automendes.backend.mapper.VehicleMapper;
import com.automendes.backend.service.VehicleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/vehicles")
public class VehicleController {
	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private VehicleMapper vehicleMapper;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/register-vehicle")
	public ResponseEntity<VehicleResponseDTO> registerVehicle(@Valid @RequestBody VehicleRequestDTO dto) {
		Vehicle vehicle = vehicleService.registerVehicle(vehicleMapper.toVehicle(dto));

		return ResponseEntity.status(HttpStatus.CREATED).body(vehicleMapper.toVehicleResponseDTO(vehicle));
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/list-vehicles")
	public ResponseEntity<Page<VehicleResponseDTO>> listVehicles(Pageable pageable) {
		Page<Vehicle> page = vehicleService.listVehicles(pageable);

		return ResponseEntity.status(HttpStatus.CREATED).body(page.map(vehicleMapper::toVehicleResponseDTO));
	}
}