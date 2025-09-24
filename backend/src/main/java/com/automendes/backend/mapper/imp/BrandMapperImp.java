package com.automendes.backend.mapper.imp;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.automendes.backend.dto.BrandRequestDTO;
import com.automendes.backend.dto.BrandResponseDTO;
import com.automendes.backend.entity.Brand;
import com.automendes.backend.mapper.BrandMapper;

@Component
public class BrandMapperImp implements BrandMapper {
	public Brand toBrand(BrandRequestDTO dto) {
		//Brand brand = new Brand(null, dto.name(), null);
		Brand brand = new Brand();
		
		BeanUtils.copyProperties(dto, brand);

		return brand;
	}

	public BrandResponseDTO toBrandResponseDTO(Brand entity) {
		//BrandResponseDTO brandResponseDTO = new BrandResponseDTO(entity.getId(), entity.getName());
		BrandResponseDTO brandResponseDTO = new BrandResponseDTO();
		
		BeanUtils.copyProperties(entity, brandResponseDTO);
		
		return brandResponseDTO;
	}
}
