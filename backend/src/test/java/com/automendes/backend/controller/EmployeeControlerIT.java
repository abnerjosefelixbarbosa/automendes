package com.automendes.backend.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
import com.automendes.backend.entity.Employee;
import com.automendes.backend.enums.EmployeeType;
import com.automendes.backend.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.uuid.Generators;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class EmployeeControlerIT {
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ObjectMapper objectMapper;
	@Autowired
	private EmployeeRepository employeeRepository;

	@BeforeEach
	void setUp() throws Exception {
		employeeRepository.deleteAll();
	}

	@AfterEach
	void tearDown() throws Exception {
		employeeRepository.deleteAll();
	}

	@Test
	void shouldRegisterEmployeeAndReturnStatus201() throws Exception {
		//loadEmployee();
		
		EmployeeRequestDTO employeeRequestDTO = new EmployeeRequestDTO("name1", "email1@gmail.com", "1111111111",
				"81911111111", LocalDate.now().withYear(1991), new BigDecimal("10.00"), EmployeeType.SELLER);

		String object = objectMapper.writeValueAsString(employeeRequestDTO);

		mockMvc.perform(post("/employees/register-employee").contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON).content(object)).andExpect(status().isCreated()).andDo(print());
	}
	
	void loadEmployee() {
		String uuid = Generators.timeBasedEpochRandomGenerator().generate().toString();
		
		Employee employee = new Employee(uuid, "name1", "email1@gmail.com", "1111111111",
				"81911111111", LocalDate.now().withYear(1991), new BigDecimal("10.00"), EmployeeType.SELLER, null);
		
		employeeRepository.save(employee);
	}
}