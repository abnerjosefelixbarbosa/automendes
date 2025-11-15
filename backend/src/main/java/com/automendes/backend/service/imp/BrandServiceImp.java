package com.automendes.backend.service.imp;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.automendes.backend.entity.Brand;
import com.automendes.backend.exception.NotFoundException;
import com.automendes.backend.repository.BrandRepository;
import com.automendes.backend.service.BrandService;
import com.automendes.backend.validation.BrandValidation;
import com.fasterxml.uuid.Generators;

import jakarta.transaction.Transactional;

@Service
public class BrandServiceImp implements BrandService {
	private BrandRepository brandRepository;
	private BrandValidation brandValidation;

	public BrandServiceImp(BrandRepository brandRepository, BrandValidation brandValidation) {
		this.brandRepository = brandRepository;
		this.brandValidation = brandValidation;
	}

	@Transactional
	public Brand registerBrand(Brand brand) {
		brandValidation.validateBrand(brand);

		brand.setId(Generators.timeBasedEpochRandomGenerator().generate().toString());

		return brandRepository.save(brand);
	}

	@Transactional
	public Brand updateBrandById(String id, Brand brand) {
		brandValidation.validateBrand(brand);

		Brand brandFound = brandRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Id deve ser existente."));

		BeanUtils.copyProperties(brand, brandFound, "id");

		return brandRepository.save(brandFound);
	}

	public Page<Brand> listBrands(Pageable pageable) {
		return brandRepository.findAll(pageable);
	}

	public Brand searchBrandById(String id) {
		return brandRepository.findById(id).orElseThrow(() -> new NotFoundException("Id deve ser existente."));
	}

	public Optional<Brand> findByName(String name) {
		return brandRepository.findByName(name);
	}
}