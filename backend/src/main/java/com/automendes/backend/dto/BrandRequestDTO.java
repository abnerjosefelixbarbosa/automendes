package com.automendes.backend.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record BrandRequestDTO(
		@NotNull(message = "Nome não deve ser nulo.")
		@NotEmpty(message = "Nome não deve ser vázio.")
		@Size(max = 30, message = "Nome deve ter no máximo 30 caracteres.")
		String name
) {}