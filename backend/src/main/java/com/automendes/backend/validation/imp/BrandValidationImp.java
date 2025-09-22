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

	public void validateBrandRegistration(Brand brand) {
		boolean isNameExists = brandRepository.existsByName(brand.getName());

		if (isNameExists) {
			throw new RuntimeException("Nome deve ser único.");
		}
	}

	public void validateBrandUpdate(Brand brand) {
		boolean isNameExists = brandRepository.existsByName(brand.getName());

		if (isNameExists) {
			throw new RuntimeException("Nome deve ser único.");
		}
	}
}
