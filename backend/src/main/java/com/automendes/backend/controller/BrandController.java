package com.automendes.backend.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
	private BrandMapper brandMapper;
	private BrandService brandService;
	
	public BrandController(BrandMapper brandMapper, BrandService brandService) {
		this.brandMapper = brandMapper;
		this.brandService = brandService;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/register-brand")
	public ResponseEntity<BrandResponseDTO> registerBrand(@RequestBody @Valid BrandRequestDTO dto) {
		Brand response = brandService.registerBrand(brandMapper.toBrand(dto));

		return ResponseEntity.status(HttpStatus.CREATED).body(brandMapper.toBrandResponseDTO(response));
	}
	
    @ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/update-brand-by-id")
	public ResponseEntity<BrandResponseDTO> updateBrandById(@RequestParam String id, @RequestBody @Valid BrandRequestDTO dto) {
		Brand response = brandService.updateBrandById(id, brandMapper.toBrand(dto));

		return ResponseEntity.status(HttpStatus.OK).body(brandMapper.toBrandResponseDTO(response));
	}
	
    @ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/list-brands")
	public ResponseEntity<List<BrandResponseDTO>> listBrands(Pageable pageable) {
		Page<Brand> page = brandService.listBrands(pageable);

		return ResponseEntity.status(HttpStatus.OK).body(page.map(brandMapper::toBrandResponseDTO).getContent());
	}
	
    @ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/search-brand-by-id")
	public ResponseEntity<BrandResponseDTO> searchBrandById(@RequestParam String id) {
		Brand response = brandService.searchBrandById(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(brandMapper.toBrandResponseDTO(response));
	} 
}