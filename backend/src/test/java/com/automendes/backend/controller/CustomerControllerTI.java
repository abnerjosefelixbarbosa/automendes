package com.automendes.backend.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
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

import com.automendes.backend.dto.CustomerRequestDTO;
import com.automendes.backend.entity.Customer;
import com.automendes.backend.enums.CustomerType;
import com.automendes.backend.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.uuid.Generators;

@SpringBootTest
@ActiveProfiles("dev")
@AutoConfigureMockMvc
class CustomerControllerTI {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private CustomerRepository customerRepository;
	private Customer customer;

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
		CustomerRequestDTO customerRequestDTO = new CustomerRequestDTO("52026255024", "nome1", "email1@gmail.com",
				"81911111111", CustomerType.PF);

		String object = objectMapper.writeValueAsString(customerRequestDTO);

		mockMvc.perform(post("/customers/register-customer").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(object))
		.andExpect(status().isCreated())
		.andDo(print());
	}
	
	@Test
	void shouldRegisterCustomerWithExistsDocumentOrExistsNameOrExistsEmailOrExistsPhoneAndReturnStatus400() throws Exception {
		loadCustomers();
		
		CustomerRequestDTO customerRequestDTO = new CustomerRequestDTO("52026255024", "nome1", "email1@gmail.com",
				"81911111111", CustomerType.PF);

		String object = objectMapper.writeValueAsString(customerRequestDTO);

		mockMvc.perform(post("/customers/register-customer").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(object))
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.message").value("Documento, nome, email ou telefone não deve ser repetido."))
		.andDo(print());
	}
	
	@Test
	void shouldRegisterCustomerWithInvalidDocumentCpfAndReturnStatus400() throws Exception {
		CustomerRequestDTO customerRequestDTO = new CustomerRequestDTO("52026255021", "nome1", "email1@gmail.com",
				"81911111111", CustomerType.PF);

		String object = objectMapper.writeValueAsString(customerRequestDTO);

		mockMvc.perform(post("/customers/register-customer").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(object))
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.message").value("Documento cpf deve ser valido."))
		.andDo(print());
	}
	
	@Test
	void shouldRegisterCustomerWithInvalidDocumentCnpjAndReturnStatus400() throws Exception {
		CustomerRequestDTO customerRequestDTO = new CustomerRequestDTO("16496627000111", "nome1", "email1@gmail.com",
				"81911111111", CustomerType.PJ);

		String object = objectMapper.writeValueAsString(customerRequestDTO);

		mockMvc.perform(post("/customers/register-customer").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(object))
		.andExpect(status().isBadRequest())
		.andExpect(jsonPath("$.message").value("Documento cnpj deve ser valido."))
		.andDo(print());
	}

	@Test
	void shouldUpdateCustomerByIdAndReturnStatus200() throws Exception {
		loadCustomers();

		CustomerRequestDTO customerRequestDTO = new CustomerRequestDTO("06199835077", "nome2", "email2@gmail.com",
				"81911111112", CustomerType.PF);

		String object = objectMapper.writeValueAsString(customerRequestDTO);

		mockMvc.perform(put("/customers/update-customer-by-id").queryParam("id", customer.getId() + "")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(object))
				.andExpect(status().isOk())
				.andDo(print());
	}
	
	@Test
	void shouldUpdateCustomerByIdWithNotExistsIdAndReturnStatus404() throws Exception {
		loadCustomers();

		CustomerRequestDTO customerRequestDTO = new CustomerRequestDTO("06199835077", "nome2", "email2@gmail.com",
				"81911111112", CustomerType.PF);

		String object = objectMapper.writeValueAsString(customerRequestDTO);

		mockMvc.perform(put("/customers/update-customer-by-id").queryParam("id", customer.getId() + "1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON).content(object))
		        .andExpect(jsonPath("$.message").value("Id deve existir."))
				.andExpect(status().isNotFound())
				.andDo(print());
	}

	@Test
	void shouldSearchCustomerByDocumentAndReturnStatus200() throws Exception {
		loadCustomers();

		mockMvc.perform(get("/customers/search-customer-by-document").queryParam("document", customer.getDocument()))
				.andExpect(status().isOk())
				.andDo(print());
	}

	void loadCustomers() {
		List<Customer> customers = new ArrayList<>(); 
		
		customers.add(new Customer(Generators.timeBasedEpochRandomGenerator().generate().toString(), "52026255024", "nome1",
				"email1@gmail.com", "81911111111", CustomerType.PF, null));
		
		customerRepository.saveAll(customers);
		
		customer = customers.get(0);
	}
}