package com.automendes.backend.dto;

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
	@NotEmpty(message = "Nome não deve ser vázio.")
	@Size(max = 30, message = "Nome deve ter no máximo 30 caracteres.")
	private String name;
}