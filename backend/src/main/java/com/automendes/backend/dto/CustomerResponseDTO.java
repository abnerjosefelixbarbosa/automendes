package com.automendes.backend.dto;

import com.automendes.backend.enums.CustomerType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerResponseDTO {
	private String id;
	private String document;
	private String name;
	private String email;
	private String phone;
	private CustomerType customerType;
}