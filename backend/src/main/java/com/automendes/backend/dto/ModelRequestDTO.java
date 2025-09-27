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
	@NotNull(message = "Nome deve ser obrigatório.")
	@NotEmpty(message = "Nome deve ser obrigatório.")
	@Size(max = 30, message = "Nome deve ter no máximo 30 caracteres.")
	private String name;
	@NotNull(message = "Nome da marca deve ser obrigatório.")
	@NotEmpty(message = "Nome da marca deve ser obrigatório.")
	@Size(max = 30, message = "Nome da marca deve ter no máximo 30 caracteres.")
	private String brandName;
}