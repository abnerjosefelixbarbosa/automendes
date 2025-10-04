package com.automendes.backend.dto;

import java.math.BigDecimal;

import com.automendes.backend.enums.BoxgearType;
import com.automendes.backend.enums.VehicleType;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleRequestDTO {
	@Size(message = "Placa deve ter no máximo 20 caracteres.", max = 20)
	private String plate;
	@NotNull(message = "Nome do modelo deve ser obrigatório.")
	@NotEmpty(message = "Nome do modelo deve ser obrigatório.")
	@Size(message = "Nome do modelo deve ter no máximo 30 caracteres.", max = 30)
	private String modelName;
	@NotNull(message = "Preço deve ser obrigatório.")
	private BigDecimal price;
	@NotNull(message = "Tipo de câmbio deve ser obrigatório.")
	private BoxgearType boxgearType;
	@NotNull(message = "Tipo de veículo deve ser obrigatório.")
	private VehicleType vehicleType;
}