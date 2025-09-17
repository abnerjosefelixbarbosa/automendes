package com.automendes.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.automendes.backend.enums.EmployeeType;

public record EmployeeResponseDTO(
		String id,
		String name,
		String email,
		String matriculation,
		String phone,
		LocalDate birthDate,
		BigDecimal commission,
		EmployeeType employeeType
) {}