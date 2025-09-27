package com.automendes.backend.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.automendes.backend.dto.EmployeeRequestDTO;
import com.automendes.backend.dto.ModelRequestDTO;
import com.automendes.backend.entity.Brand;
import com.automendes.backend.entity.Model;
import com.automendes.backend.enums.EmployeeType;
import com.automendes.backend.repository.BrandRepository;
import com.automendes.backend.repository.ModelRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.uuid.Generators;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class ModelControllerTI {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private ModelRepository modelRepository;
	@Autowired
	private BrandRepository brandRepository;
	private String id;
	private Brand brand;

	@BeforeEach
	void setUp() throws Exception {
		modelRepository.deleteAll();
		brandRepository.deleteAll();
	}

	@AfterEach
	void tearDown() throws Exception {
		modelRepository.deleteAll();
		brandRepository.deleteAll();
	}

	@Test
	void shouldRegisterModelAndReturnStatus201() throws Exception {
		loadBrand();
		
		ModelRequestDTO modelRequestDTO = new ModelRequestDTO("nome1", "nome1");

		String object = objectMapper.writeValueAsString(modelRequestDTO);

		mockMvc.perform(post("/models/register-model").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(object)).andExpect(status().isCreated()).andDo(print());
	}

	@Test
	void shouldUpdateModelByIdAndReturnStatus200() throws Exception {
		loadModel();

		EmployeeRequestDTO employeeRequestDTO = new EmployeeRequestDTO("name2", "email2@gmail.com", "1111111112",
				"81911111112", LocalDate.now().withYear(1991), new BigDecimal("10.00"), EmployeeType.MANAGER);

		String object = objectMapper.writeValueAsString(employeeRequestDTO);

		mockMvc.perform(put("/models/update-model-by-id").queryParam("id", id + "")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(object))
				.andExpect(status().isOk()).andDo(print());
	}

	@Test
	void shouldListModelsAndReturnStatus200() throws Exception {
		loadModel();

		mockMvc.perform(get("/models/list-models")).andExpect(status().isOk()).andDo(print());
	}

	void loadBrand() {
		Brand brand1 = new Brand(Generators.timeBasedEpochRandomGenerator().generate().toString(), "nome1", null);

		brand = brandRepository.save(brand1);
	}

	void loadModel() {
		Model model1 = new Model(Generators.timeBasedEpochRandomGenerator().generate().toString(), "nome1", brand,
				null);

		id = modelRepository.save(model1).getId();
	}
}
