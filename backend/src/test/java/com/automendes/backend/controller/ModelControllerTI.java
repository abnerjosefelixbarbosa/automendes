package com.automendes.backend.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
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

import com.automendes.backend.dto.ModelRequestDTO;
import com.automendes.backend.entity.Brand;
import com.automendes.backend.entity.Model;
import com.automendes.backend.repository.BrandRepository;
import com.automendes.backend.repository.ModelRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.uuid.Generators;

@SpringBootTest
@ActiveProfiles("dev")
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
	private Model model;
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
		loadBrands();
		
		loadModels();
		
		ModelRequestDTO modelRequestDTO = new ModelRequestDTO("nome2", "nome1");

		String object = objectMapper.writeValueAsString(modelRequestDTO);

		mockMvc.perform(post("/models/register-model").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(object))
		.andExpect(status().isCreated())
		.andDo(print());
	}

	@Test
	void shouldUpdateModelByIdAndReturnStatus200() throws Exception {
		loadBrands();
		
		loadModels();
		
		ModelRequestDTO modelRequestDTO = new ModelRequestDTO("nome2", "nome1");

		String object = objectMapper.writeValueAsString(modelRequestDTO);

		mockMvc.perform(put("/models/update-model-by-id").queryParam("id", model.getId() + "")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(object))
		.andExpect(status().isOk())
		.andDo(print());
	}

	@Test
	void shouldListModelsAndReturnStatus200() throws Exception {
		loadBrands();
		
		loadModels();

		mockMvc.perform(get("/models/list-models"))
		.andExpect(status().isOk())
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
		
		models.add(new Model(Generators.timeBasedEpochRandomGenerator().generate().toString(), "nome1", brand,
				null));
		
		modelRepository.saveAll(models);
		
		model = models.get(0);
	}
}
