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
		BrandRequestDTO brandRequestDTO = new BrandRequestDTO("nome1");

		String object = objectMapper.writeValueAsString(brandRequestDTO);

		mockMvc.perform(post("/brands/register-brand").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(object))
		.andExpect(status().isCreated())
		.andDo(print());
	}
	
	@Test
	void shouldRegisterBrandWithNullNameAndReturnStatus400() throws Exception {
		BrandRequestDTO brandRequestDTO = new BrandRequestDTO(null);

		String object = objectMapper.writeValueAsString(brandRequestDTO);

		mockMvc.perform(post("/brands/register-brand").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(object))
		.andExpect(status().isBadRequest())
		.andDo(print());
	}
	
	@Test
	void shouldRegisterBrandWithEmptyNameAndReturnStatus400() throws Exception {
		BrandRequestDTO brandRequestDTO = new BrandRequestDTO("");

		String object = objectMapper.writeValueAsString(brandRequestDTO);

		mockMvc.perform(post("/brands/register-brand").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(object))
		.andExpect(status().isBadRequest())
		.andDo(print());
	}
	
	@Test
	void shouldRegisterBrandWithNameSize31AndReturnStatus400() throws Exception {
		BrandRequestDTO brandRequestDTO = new BrandRequestDTO("nome111111111111111111111111111");

		String object = objectMapper.writeValueAsString(brandRequestDTO);

		mockMvc.perform(post("/brands/register-brand").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(object))
		.andExpect(status().isBadRequest())
		.andDo(print());
	}
	
	@Test
	void shouldRegisterBrandWithExistentNameAndReturnStatus400() throws Exception {
		loadBrands();
		
		BrandRequestDTO brandRequestDTO = new BrandRequestDTO("nome1");

		String object = objectMapper.writeValueAsString(brandRequestDTO);

		mockMvc.perform(post("/brands/register-brand").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(object))
		.andExpect(status().isBadRequest())
		.andDo(print());
	}

	@Test
	void shouldUpdateBrandByIdAndReturnStatus200() throws Exception {
		loadBrands();

		BrandRequestDTO brandRequestDTO = new BrandRequestDTO("nome2");

		String object = objectMapper.writeValueAsString(brandRequestDTO);

		mockMvc.perform(put("/brands/update-brand-by-id").queryParam("id", id + "")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(object))
		.andExpect(status().isOk())
		.andDo(print());
	}
	
	@Test
	void shouldUpdateBrandByIdWithNotExistentIdAndReturnStatus404() throws Exception {
		loadBrands();

		BrandRequestDTO brandRequestDTO = new BrandRequestDTO("nome2");

		String object = objectMapper.writeValueAsString(brandRequestDTO);

		mockMvc.perform(put("/brands/update-brand-by-id").queryParam("id", id + "1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(object))
				.andExpect(status().isNotFound()).andDo(print());
	}

	@Test
	void shouldListBrandsAndReturnStatus200() throws Exception {
		loadBrands();

		mockMvc.perform(get("/brands/list-brands")).andExpect(status().isOk()).andDo(print());
	}

	void loadBrands() {
		Brand brand1 = new Brand(Generators.timeBasedEpochRandomGenerator().generate().toString(), "nome1", null);
 
		id = brandRepository.save(brand1).getId();
	}
}
