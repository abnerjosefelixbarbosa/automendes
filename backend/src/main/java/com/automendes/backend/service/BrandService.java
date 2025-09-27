package com.automendes.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.automendes.backend.entity.Brand;

public interface BrandService {
	Brand registerBrand(Brand brand);
	
	Brand updateBrandById(String id, Brand brand);
	
	Page<Brand> listBrands(Pageable pageable); 
	
	Brand findBrandByName(String name);
}