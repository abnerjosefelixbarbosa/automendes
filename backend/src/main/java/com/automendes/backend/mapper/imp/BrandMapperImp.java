package com.automendes.backend.mapper.imp;

import org.springframework.stereotype.Component;

import com.automendes.backend.dto.BrandRequestDTO;
import com.automendes.backend.dto.BrandResponseDTO;
import com.automendes.backend.entity.Brand;
import com.automendes.backend.mapper.BrandMapper;

@Component
public class BrandMapperImp implements BrandMapper {
	public Brand toBrand(BrandRequestDTO dto) {
		Brand brand = new Brand(null, dto.name(), null);

		return brand;
	}

	public BrandResponseDTO toBrandResponseDTO(Brand entity) {
		BrandResponseDTO brandResponseDTO = new BrandResponseDTO(entity.getId(), entity.getName());

		return brandResponseDTO;
	}
}
