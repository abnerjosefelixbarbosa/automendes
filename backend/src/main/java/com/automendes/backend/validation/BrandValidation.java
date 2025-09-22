package com.automendes.backend.validation;

import com.automendes.backend.entity.Brand;

public interface BrandValidation {
	void validateBrandRegistration(Brand brand);
	
	void validateBrandUpdate(Brand brand);
}