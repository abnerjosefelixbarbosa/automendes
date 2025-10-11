package com.automendes.backend.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
		loadBrands();
		
		loadModels();

		VehicleRequestDTO vehicleRequestDTO = new VehicleRequestDTO("", "nome1", new BigDecimal("1500.00"),
				BoxgearType.AUTO, VehicleType.CAR);

		String object = objectMapper.writeValueAsString(vehicleRequestDTO);

		mockMvc.perform(post("/vehicles/register-vehicle").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(object))
		.andExpect(status().isCreated())
		.andDo(print());
	}
	
	@Test
	void shouldRegisterVehicleWithPlateSize21AndReturnStatus400() throws Exception {
		loadBrands();
		
		loadModels();

		VehicleRequestDTO vehicleRequestDTO = new VehicleRequestDTO("A1111-111111111111111", "nome1", new BigDecimal("1500.00"),
				BoxgearType.AUTO, VehicleType.CAR);

		String object = objectMapper.writeValueAsString(vehicleRequestDTO);

		mockMvc.perform(post("/vehicles/register-vehicle").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(object))
		.andExpect(status().isBadRequest())
		.andDo(print());
	}
	
	@Test
	void shouldRegisterVehicleWithNullModelNameAndReturnStatus400() throws Exception {
		loadBrands();
		
		loadModels();

		VehicleRequestDTO vehicleRequestDTO = new VehicleRequestDTO("", null, new BigDecimal("1500.00"),
				BoxgearType.AUTO, VehicleType.CAR);

		String object = objectMapper.writeValueAsString(vehicleRequestDTO);

		mockMvc.perform(post("/vehicles/register-vehicle").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(object))
		.andExpect(status().isBadRequest())
		.andDo(print());
	}
	
	@Test
	void shouldRegisterVehicleWithEmptyModelNameAndReturnStatus400() throws Exception {
		loadBrands();
		
		loadModels();

		VehicleRequestDTO vehicleRequestDTO = new VehicleRequestDTO("", "", new BigDecimal("1500.00"),
				BoxgearType.AUTO, VehicleType.CAR);

		String object = objectMapper.writeValueAsString(vehicleRequestDTO);

		mockMvc.perform(post("/vehicles/register-vehicle").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(object))
		.andExpect(status().isBadRequest())
		.andDo(print());
	}
	
	@Test
	void shouldRegisterVehicleWithModelNameSize31AndReturnStatus400() throws Exception {
		loadBrands();
		
		loadModels();

		VehicleRequestDTO vehicleRequestDTO = new VehicleRequestDTO("", "nome111111111111111111111111111", new BigDecimal("1500.00"),
				BoxgearType.AUTO, VehicleType.CAR);

		String object = objectMapper.writeValueAsString(vehicleRequestDTO);

		mockMvc.perform(post("/vehicles/register-vehicle").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(object))
		.andExpect(status().isBadRequest())
		.andDo(print());
	}
	
	@Test
	void shouldRegisterVehicleWithNotExistsModelNameAndReturnStatus404() throws Exception {
		loadBrands();
		
		loadModels();

		VehicleRequestDTO vehicleRequestDTO = new VehicleRequestDTO("", "nome2", new BigDecimal("1500.00"),
				BoxgearType.AUTO, VehicleType.CAR);

		String object = objectMapper.writeValueAsString(vehicleRequestDTO);

		mockMvc.perform(post("/vehicles/register-vehicle").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(object))
		.andExpect(status().isNotFound())
		.andDo(print());
	}
	
	@Test
	void shouldRegisterVehicleWithNullPriceAndReturnStatus400() throws Exception {
		loadBrands();
		
		loadModels();

		VehicleRequestDTO vehicleRequestDTO = new VehicleRequestDTO("", "nome1", null,
				BoxgearType.AUTO, VehicleType.CAR);

		String object = objectMapper.writeValueAsString(vehicleRequestDTO);

		mockMvc.perform(post("/vehicles/register-vehicle").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(object))
		.andExpect(status().isBadRequest())
		.andDo(print());
	}
	
	@Test
	void shouldRegisterVehicleWithPrice0AndReturnStatus400() throws Exception {
		loadBrands();
		
		loadModels();

		VehicleRequestDTO vehicleRequestDTO = new VehicleRequestDTO("", "nome1", new BigDecimal("0.00"),
				BoxgearType.AUTO, VehicleType.CAR);

		String object = objectMapper.writeValueAsString(vehicleRequestDTO);

		mockMvc.perform(post("/vehicles/register-vehicle").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(object))
		.andExpect(status().isBadRequest())
		.andDo(print());
	}
	
	@Test
	void shouldRegisterVehicleWith1DigitPriceAndReturnStatus400() throws Exception {
		loadBrands();
		
		loadModels();

		VehicleRequestDTO vehicleRequestDTO = new VehicleRequestDTO("", "nome1", new BigDecimal("0.0"),
				BoxgearType.AUTO, VehicleType.CAR);

		String object = objectMapper.writeValueAsString(vehicleRequestDTO);

		mockMvc.perform(post("/vehicles/register-vehicle").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(object))
		.andExpect(status().isBadRequest())
		.andDo(print());
	}
	
	@Test
	void shouldRegisterVehicleWithNullBoxgearTypeAndReturnStatus400() throws Exception {
		loadBrands();
		
		loadModels();

		VehicleRequestDTO vehicleRequestDTO = new VehicleRequestDTO("", "nome1", new BigDecimal("1500.00"),
				null, VehicleType.CAR);

		String object = objectMapper.writeValueAsString(vehicleRequestDTO);

		mockMvc.perform(post("/vehicles/register-vehicle").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(object))
		.andExpect(status().isBadRequest())
		.andDo(print());
	}
	
	@Test
	void shouldRegisterVehicleWithNullVehicleTypeAndReturnStatus400() throws Exception {
		loadBrands();
		
		loadModels();

		VehicleRequestDTO vehicleRequestDTO = new VehicleRequestDTO("", "nome1", new BigDecimal("1500.00"),
				BoxgearType.AUTO, null);

		String object = objectMapper.writeValueAsString(vehicleRequestDTO);

		mockMvc.perform(post("/vehicles/register-vehicle").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(object))
		.andExpect(status().isBadRequest())
		.andDo(print());
	}

	void loadBrands() {
		List<Brand> brands = new ArrayList<>();

		brands.add(new Brand(Generators.timeBasedEpochRandomGenerator().generate().toString(), "nome1", null));

		brandRepository.saveAll(brands);

		brand = brands.get(0);
	}

	void loadModels() {
		List<Model> models = new ArrayList<>();

		models.add(new Model(Generators.timeBasedEpochRandomGenerator().generate().toString(), "nome1", brand, null));

		modelRepository.saveAll(models);

		model = models.get(0);
	}

	void loadVehicles() {
		List<Vehicle> vehicles = new ArrayList<>();

		vehicles.add(new Vehicle(Generators.timeBasedEpochRandomGenerator().generate().toString(), "",
				new BigDecimal("1500.00"), BoxgearType.AUTO, VehicleType.CAR, model, null));

		vehicleRepository.saveAll(vehicles);

		vehicle = vehicles.get(0);
	}
}