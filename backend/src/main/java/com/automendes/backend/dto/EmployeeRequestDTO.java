package com.automendes.backend.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.automendes.backend.enums.EmployeeType;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequestDTO {
	@NotEmpty(message = "Nome não deve ser vazio.")
	@NotNull(message = "Nome não deve ser nulo.")
	@Size(message = "Nome não deve ter mais de 100 caracteres.", max = 100)
	private String name;
	@NotEmpty(message = "Email não deve ser vazio.")
	@NotNull(message = "Email não deve ser null.")
	@Size(message = "Email não deve ter mais de 50 caracteres.", max = 50)
	@Email(message = "Email deve ser valido.")
	private String email;
	@NotEmpty(message = "Matrícula não deve ser vazia.")
	@NotNull(message = "Matrícula não deve ser null.")
	@Pattern(message = "Matrícula deve ter 10 caracteres numericos.", regexp = "^\\d{10}$")
	//@Size(message = "", min = 10, max = 10)
	private String matriculation;
	@NotEmpty(message = "Telefone não deve ser vazio.")
	@NotNull(message = "Telefone não deve ser nulo.")
	@Size(message = "Telefone não deve ter mais de 20 caracteres.")
	private String phone;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Data de nascimento não deve ser nulo.")
	@Past(message = "Data de nascimeto não deve ser atual.")
	private LocalDate birthDate;
	private BigDecimal commission;
	@NotNull(message = "Tipo do funcionário não deve ser nulo.")
	private EmployeeType employeeType;
}