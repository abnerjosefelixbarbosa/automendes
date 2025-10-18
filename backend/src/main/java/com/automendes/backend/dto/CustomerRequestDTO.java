package com.automendes.backend.dto;

import com.automendes.backend.enums.CustomerType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerRequestDTO {
	@NotNull(message = "Documento não deve ser nulo.")
	@NotEmpty(message = "Documento não deve ser vazio.")
	@Size(message = "Documento não deve ter mais de 14 caracteres.", max = 14)
	private String document;
	@NotNull(message = "Nome não deve ser nulo.")
	@NotEmpty(message = "Nome não deve ser vazio.")
	@Size(max = 100, message = "Nome não deve ter 100 caracteres.")
	private String name;
	@NotNull(message = "Email não deve ser nulo.")
	@NotEmpty(message = "Email não deve ser vazio.")
	@Size(max = 50, message = "Email não deve ter mais de 50 caracteres.")
	@Email(message = "Email deve ser valido.")
	private String email;
	@NotNull(message = "Telefone não deve ser nulo.")
	@NotEmpty(message = "Telefone não deve ser vazio.")
	@Size(max = 30, message = "Telefone não deve ter mais de 30 caracteres.")
	private String phone;
	@NotNull(message = "Tipo do cliente não deve ser nulo.")
	private CustomerType customerType;
}