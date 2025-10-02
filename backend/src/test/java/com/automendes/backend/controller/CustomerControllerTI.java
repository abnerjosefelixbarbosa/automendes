package com.automendes.backend.controller;

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

import com.automendes.backend.dto.CustomerRequestDTO;
import com.automendes.backend.entity.Customer;
import com.automendes.backend.enums.CustomerType;
import com.automendes.backend.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.uuid.Generators;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class CustomerControllerTI {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private CustomerRepository customerRepository;
	private String id;

	@BeforeEach
	void setUp() throws Exception {
		customerRepository.deleteAll();
	}

	@AfterEach
	void tearDown() throws Exception {
		customerRepository.deleteAll();
	}

	@Test
	void shouldRegisterCustomerAndReturnStatus201() throws Exception {
		//loadCustomers();
		
		//CustomerRequestDTO customerRequestDTO = new CustomerRequestDTO("57785719000100", "nome1", "email1@gmail.com", "81911111111",
		//		CustomerType.PJ);
		
		CustomerRequestDTO customerRequestDTO = new CustomerRequestDTO("52026255024", "nome1", "email1@gmail.com", "81911111111",
				CustomerType.PJ);

		String object = objectMapper.writeValueAsString(customerRequestDTO);

		mockMvc.perform(post("/customers/register-customer").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(object)).andExpect(status().isCreated()).andDo(print());
	}
	
	@Test
	void shouldUpdateCustomerByIdAndReturnStatus200() throws Exception {
		loadCustomers();
		
		CustomerRequestDTO customerRequestDTO = new CustomerRequestDTO("06199835077", "nome2", "email2@gmail.com", "81911111112",
				CustomerType.PF);

		String object = objectMapper.writeValueAsString(customerRequestDTO);

		mockMvc.perform(put("/customers/update-customer-by-id").queryParam("id", id).contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(object)).andExpect(status().isOk()).andDo(print());
	}

	void loadCustomers() {
		Customer customer = new Customer(Generators.timeBasedEpochRandomGenerator().toString(),"52026255024", "nome1", "email1@gmail.com",
				"81911111111", CustomerType.PF, null);
		
		id = customerRepository.save(customer).getId();
	}
}