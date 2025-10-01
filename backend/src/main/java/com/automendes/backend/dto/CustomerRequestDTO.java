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
	@NotNull(message = "Documento deve ser obrigatório.")
	@NotEmpty(message = "Documento deve ser obrigatório.")
	@Size(message = "Documento deve ter até 14 caracteres.", max = 14)
	private String document;
	@NotNull(message = "Nome deve ser obrigatório.")
	@NotEmpty(message = "Nome deve ser obrigatório.")
	@Size(max = 100, message = "Nome deve ter 100 caracteres.")
	private String name;
	@NotNull(message = "Email deve ser obrigatório.")
	@NotEmpty(message = "Email deve ser obrigatório.")
	@Size(max = 50, message = "Email deve ter até 50 caracteres.")
	@Email(message = "Email deve ser valido.")
	private String email;
	@NotNull(message = "Telefone deve ser obrigatório.")
	@NotEmpty(message = "Telefone deve ser obrigatório.")
	@Size(max = 30, message = "Telefone deve ter até 30 caracteres.")
	private String phone;
	@NotNull(message = "Tipo do cliente deve ser obrigatório.")
	private CustomerType customerType;
}