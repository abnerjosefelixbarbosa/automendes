package com.automendes.backend.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandRequestDTO {
	@NotNull(message = "Nome não deve ser nulo.")
	@NotEmpty(message = "Nome não deve ser vazio.")
	@NotBlank(message = "Nome não deve ter espaço vazio.")
	@Size(max = 30, message = "Nome não deve ter mais de 30 caracteres.")
	private String name;
}