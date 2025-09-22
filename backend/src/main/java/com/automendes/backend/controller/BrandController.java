package com.automendes.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.automendes.backend.dto.BrandRequestDTO;
import com.automendes.backend.dto.BrandResponseDTO;
import com.automendes.backend.entity.Brand;
import com.automendes.backend.mapper.BrandMapper;
import com.automendes.backend.service.BrandService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/brands")
public class BrandController {
	@Autowired
	private BrandMapper brandMapper;
	@Autowired
	private BrandService brandService;

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/register-brand")
	public ResponseEntity<BrandResponseDTO> registerBrand(@RequestBody @Valid BrandRequestDTO dto) {
		Brand brand = brandService.registerBrand(brandMapper.toBrand(dto));

		return ResponseEntity.status(HttpStatus.CREATED).body(brandMapper.toBrandResponseDTO(brand));
	}
}