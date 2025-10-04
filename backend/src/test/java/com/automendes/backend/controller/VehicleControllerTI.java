package com.automendes.backend.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.automendes.backend.dto.VehicleRequestDTO;
import com.automendes.backend.entity.Brand;
import com.automendes.backend.entity.Model;
import com.automendes.backend.entity.Vehicle;
import com.automendes.backend.enums.BoxgearType;
import com.automendes.backend.enums.VehicleType;
import com.automendes.backend.repository.BrandRepository;
import com.automendes.backend.repository.ModelRepository;
import com.automendes.backend.repository.VehicleRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.uuid.Generators;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class VehicleControllerTI {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private VehicleRepository vehicleRepository;
	@Autowired
	private BrandRepository brandRepository;
	@Autowired
	private ModelRepository modelRepository;
	private String id;
	private Model model;
	private Brand brand;
	private Vehicle vehicle;

	@BeforeEach
	void setUp() throws Exception {
		vehicleRepository.deleteAll();
		modelRepository.deleteAll();
		brandRepository.deleteAll();
		
	}

	@AfterEach
	void tearDown() throws Exception {
		vehicleRepository.deleteAll();
		modelRepository.deleteAll();
		brandRepository.deleteAll();
	}

	@Test
	void shouldRegisterVehicleAndReturnStatus201() throws Exception {
		loadModels();
		
		VehicleRequestDTO vehicleRequestDTO = new VehicleRequestDTO("", model.getName(), new BigDecimal("1500.00"),
				BoxgearType.AUTO, VehicleType.CAR);

		String object = objectMapper.writeValueAsString(vehicleRequestDTO);

		mockMvc.perform(post("/vehicles/register-vehicle").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(object)).andExpect(status().isCreated()).andDo(print());
	}
	
	void loadBrands() {
		Brand brand1 = new Brand(Generators.timeBasedEpochRandomGenerator().generate().toString(), "nome1", null);

		brand = brandRepository.save(brand1);
	}
	
	void loadModels() {
		loadBrands();
		
		Model model1 = new Model(Generators.timeBasedEpochRandomGenerator().generate().toString(), "nome1", brand, null);
		
		model = modelRepository.save(model1);
	}
	
	void loadVehicles() {
		loadModels();
		
		Vehicle vehicle1 = new Vehicle(Generators.timeBasedEpochRandomGenerator().generate().toString(), "", new BigDecimal("1500.00"), BoxgearType.AUTO, VehicleType.CAR, model, null);
		
		vehicle = vehicleRepository.save(vehicle1);
	}
}