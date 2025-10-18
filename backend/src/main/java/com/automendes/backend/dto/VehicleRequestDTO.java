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
	@Size(message = "Placa não deve ter mais de 20 caracteres.", max = 20)
	private String plate;
	@NotNull(message = "Nome do modelo não deve ser nulo.")
	@NotEmpty(message = "Nome do modelo não deve ser vazio.")
	@Size(message = "Nome do modelo não deve ter mais de 30 caracteres.", max = 30)
	private String modelName;
	@NotNull(message = "Preço não deve ser nulo.")
	private BigDecimal price;
	@NotNull(message = "Tipo de câmbio não deve ser nulo.")
	private BoxgearType boxgearType;
	@NotNull(message = "Tipo de veículo não deve ser nulo.")
	private VehicleType vehicleType;
}