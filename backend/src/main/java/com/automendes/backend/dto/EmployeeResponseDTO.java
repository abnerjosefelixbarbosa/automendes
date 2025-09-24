package com.automendes.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.automendes.backend.enums.EmployeeType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponseDTO {
	private String id;
	private String name;
	private String email;
	private String matriculation;
	private String phone;
	private LocalDate birthDate;
	private BigDecimal commission;
	private EmployeeType employeeType;
}