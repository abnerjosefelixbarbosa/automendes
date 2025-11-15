package com.automendes.backend.validation.imp;

import org.springframework.stereotype.Component;

import com.automendes.backend.entity.Brand;
import com.automendes.backend.repository.BrandRepository;
import com.automendes.backend.validation.BrandValidation;

@Component
public class BrandValidationImp implements BrandValidation {
	private BrandRepository brandRepository;

	public BrandValidationImp(BrandRepository brandRepository) {
		this.brandRepository = brandRepository;
	}

	public void validateBrand(Brand brand) {
		if (brandRepository.existsByName(brand.getName())) {
			throw new RuntimeException("Nome não deve ser repetido.");
		}
	}
}
