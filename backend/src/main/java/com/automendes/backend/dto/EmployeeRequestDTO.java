package com.automendes.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.automendes.backend.enums.EmployeeType;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;

public record EmployeeRequestDTO(
		@NotEmpty(message = "Nome deve ser obrigatório.")
		@NotNull(message = "Nome deve ser obrigatório.")
		@Size(message = "Nome deve ter no máximo 100 caracteres.", max = 100)
		String name,
		@NotEmpty(message = "Email deve ser obrigatório.")
		@NotNull(message = "Email deve ser obrigatório.")
		@Size(message = "Email deve ter no máximo 50 caracteres.", max = 50)
		@Email(message = "Email deve ser valido.")
		String email,
		@NotEmpty(message = "Matricula deve ser obrigatória.")
		@NotNull(message = "Matricula deve ser obrigatória.")
		@Size(message = "Matricula deve ter 10 caracteres.", min = 10, max = 10)
		String matriculation,
		@NotEmpty(message = "Telefone deve ser obrigatório.")
		@NotNull(message = "Telefone deve ser obrigatório.")
		@Size(message = "Telefone deve ter no máximo 20 caracteres.")
		String phone,
		@JsonFormat(pattern = "yyyy-MM-dd")
		@NotNull(message = "Data de nascimento deve ser obrigatória.")
		@Past(message = "Data de nascimeto não deve ser atual.")
		LocalDate birthDate,
		BigDecimal commission,
		@NotNull(message = "Tipo do funcionário deve ser obrigatório.")
		EmployeeType employeeType
) {}