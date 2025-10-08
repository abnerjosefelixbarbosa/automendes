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
public class ModelRequestDTO {
	@NotNull(message = "Nome não deve ser nulo.")
	@NotEmpty(message = "Nome não deve ser vazio.")
	@Size(max = 30, message = "Nome não deve ter mais de 30 caracteres.")
	private String name;
	@NotNull(message = "Nome da marca não deve ser nulo.")
	@NotEmpty(message = "Nome da marca não deve ser vazio.")
	@Size(max = 30, message = "Nome da marca não deve ter mais de 30 caracteres.")
	private String brandName;
}