package com.automendes.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.automendes.backend.dto.VehicleRequestDTO;
import com.automendes.backend.dto.VehicleResponseDTO;
import com.automendes.backend.entity.Vehicle;
import com.automendes.backend.mapper.VehicleMapper;
import com.automendes.backend.service.VehicleService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/vehicles")
public class VehicleController {
	@Autowired
	private VehicleService vehicleService;
	@Autowired
	private VehicleMapper vehicleMapper;

	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Registra um veículo."),
		@ApiResponse(responseCode = "400", description = "Retorna um erro de requisição."),
		@ApiResponse(responseCode = "404", description = "Retorna um erro de recurso não encontrado."),
    })
    @Operation(summary = "Registrar veículo.", description = "Registra um veículo.")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/register-vehicle")
	public ResponseEntity<VehicleResponseDTO> registerVehicle(@Valid @RequestBody VehicleRequestDTO dto) {
		Vehicle vehicle = vehicleService.registerVehicle(vehicleMapper.toVehicle(dto));

		return ResponseEntity.status(HttpStatus.CREATED).body(vehicleMapper.toVehicleResponseDTO(vehicle));
	}
	
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Atualiza um veículo."),
		@ApiResponse(responseCode = "400", description = "Retorna um erro de requisição."),
		@ApiResponse(responseCode = "404", description = "Retorna um erro de recurso não encontrado."),
    })
    @Operation(summary = "Atualizar veículo pelo id.", description = "Atualiza um veículo pelo id.")
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/update-vehicle-by-id")
	public ResponseEntity<VehicleResponseDTO> updateVehicleById(@RequestParam String id, @Valid @RequestBody VehicleRequestDTO dto) {
		Vehicle vehicle = vehicleService.updateVehicleById(id, vehicleMapper.toVehicle(dto));

		return ResponseEntity.status(HttpStatus.OK).body(vehicleMapper.toVehicleResponseDTO(vehicle));
	}
	
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Lista Veículos."),
		@ApiResponse(responseCode = "400", description = "Retorna um erro de requisição."),
		@ApiResponse(responseCode = "404", description = "Retorna um erro de recurso não encontrado."),
    })
    @Operation(summary = "Listar Veículos.", description = "Lista Veículos.")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/list-vehicles")
	public ResponseEntity<List<VehicleResponseDTO>> listVehicles(Pageable pageable) {
		Page<Vehicle> page = vehicleService.listVehicles(pageable);

		return ResponseEntity.status(HttpStatus.OK).body(page.map(vehicleMapper::toVehicleResponseDTO).getContent());
	}
	
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Procura um veículo pelo id."),
		@ApiResponse(responseCode = "400", description = "Retorna um erro de requisição."),
		@ApiResponse(responseCode = "404", description = "Retorna um erro de recurso não encontrado."),
    })
    @Operation(summary = "Procurar veículo pelo id.", description = "Procura um veículo pelo id.")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/search-vehicle-by-id")
	public ResponseEntity<VehicleResponseDTO> searchVehicleById(@RequestParam String id) {
		Vehicle vehicle = vehicleService.searchVehicleById(id);

		return ResponseEntity.status(HttpStatus.OK).body(vehicleMapper.toVehicleResponseDTO(vehicle));
	}
}