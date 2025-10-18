package com.automendes.backend.validation.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.automendes.backend.entity.Brand;
import com.automendes.backend.repository.BrandRepository;
import com.automendes.backend.validation.BrandValidation;

@Component
public class BrandValidationImp implements BrandValidation {
	@Autowired
	private BrandRepository brandRepository;

	public void validateBrand(Brand brand) {
		if (brandRepository.existsByName(brand.getName())) {
			throw new RuntimeException("Nome não deve ser repetido.");
		}
	}
}
