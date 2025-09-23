package com.automendes.backend.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.automendes.backend.dto.BrandRequestDTO;
import com.automendes.backend.entity.Brand;
import com.automendes.backend.repository.BrandRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.uuid.Generators;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class BrandControllerTI {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private BrandRepository brandRepository;
	private String id;

	@BeforeEach
	void setUp() throws Exception {
		brandRepository.deleteAll();
	}

	@AfterEach
	void tearDown() throws Exception {
		brandRepository.deleteAll();
	}

	@Test
	void shouldRegisterBrandAndReturnStatus201() throws Exception {
		// loadEmployee();

		BrandRequestDTO brandRequestDTO = new BrandRequestDTO("nome1");

		String object = objectMapper.writeValueAsString(brandRequestDTO);

		mockMvc.perform(post("/brands/register-brand").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(object)).andExpect(status().isCreated()).andDo(print());
	}

	@Test
	void shouldUpdateBrandByIdAndReturnStatus200() throws Exception {
		loadBrands();

		BrandRequestDTO brandRequestDTO = new BrandRequestDTO("nome2");

		String object = objectMapper.writeValueAsString(brandRequestDTO);

		mockMvc.perform(put("/brands/update-brand-by-id").queryParam("id", id + "")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(object))
				.andExpect(status().isOk()).andDo(print());
	}

	@Test
	void shouldListBrandsAndReturnStatus200() throws Exception {
		loadBrands();

		mockMvc.perform(get("/brands/list-brands")).andExpect(status().isOk()).andDo(print());
	}

	void loadBrands() {
		String uuid1 = Generators.timeBasedEpochRandomGenerator().generate().toString();

		Brand brand1 = new Brand(uuid1, "nome1", null);

		id = brandRepository.save(brand1).getId();
	}
}
