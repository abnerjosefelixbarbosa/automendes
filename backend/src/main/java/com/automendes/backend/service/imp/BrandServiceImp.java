package com.automendes.backend.service.imp;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
	@Autowired
	private BrandRepository brandRepository;
	@Autowired
	private BrandValidation brandValidation;

	@Transactional
	public Brand registerBrand(Brand brand) {
		brandValidation.validateBrandRegistration(brand);

		String uuid = Generators.timeBasedEpochRandomGenerator().generate().toString();

		brand.setId(uuid);

		return brandRepository.save(brand);
	}

	@Transactional
	public Brand updateBrandById(String id, Brand brand) {
		brandValidation.validateBrandUpdate(brand);

		Brand brandFound = brandRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Id deve ser existente."));
		
		BeanUtils.copyProperties(brand, brandFound, "id");

		return brandRepository.save(brandFound);
	}

	public Page<Brand> listBrands(Pageable pageable) {
		return brandRepository.findAll(pageable);
	}
}