package com.automendes.backend.mapper;

import com.automendes.backend.dto.BrandRequestDTO;
import com.automendes.backend.dto.BrandResponseDTO;
import com.automendes.backend.entity.Brand;

public interface BrandMapper {
    Brand toBrand(BrandRequestDTO dto);
	
	BrandResponseDTO toBrandResponseDTO(Brand entity);
}
