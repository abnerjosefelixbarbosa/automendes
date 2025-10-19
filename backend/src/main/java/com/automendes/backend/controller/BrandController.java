package com.automendes.backend.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/brands")
public class BrandController {
	@Autowired
	private BrandMapper brandMapper;
	@Autowired
	private BrandService brandService;

	@ApiResponses({
			@ApiResponse(responseCode = "201", description = "Registra uma marca."),
			@ApiResponse(responseCode = "400", description = "Retorna um erro de requisição."),
			@ApiResponse(responseCode = "404", description = "Retorna um erro de recurso não encontrado."),
	})
	@Operation(summary = "Registrar marca.", description = "Registra uma marca.")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/register-brand")
	public ResponseEntity<BrandResponseDTO> registerBrand(@RequestBody @Valid BrandRequestDTO dto) {
		Brand brand = brandService.registerBrand(brandMapper.toBrand(dto));

		return ResponseEntity.status(HttpStatus.CREATED).body(brandMapper.toBrandResponseDTO(brand));
	}
	
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Altera uma marca."),
		@ApiResponse(responseCode = "400", description = "Retorna um erro de requisição."),
		@ApiResponse(responseCode = "404", description = "Retorna um erro de recurso não encontrado."),
    })
    @Operation(summary = "Atualizar marca pelo id.", description = "Atualiza uma marca pelo id.")
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/update-brand-by-id")
	public ResponseEntity<BrandResponseDTO> updateBrandById(@RequestParam String id, @RequestBody @Valid BrandRequestDTO dto) {
		Brand brand = brandService.updateBrandById(id, brandMapper.toBrand(dto));

		return ResponseEntity.status(HttpStatus.OK).body(brandMapper.toBrandResponseDTO(brand));
	}
	
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Lista marcas."),
		@ApiResponse(responseCode = "400", description = "Retorna um erro de requisição."),
		@ApiResponse(responseCode = "404", description = "Retorna um erro de recurso não encontrado."),
    })
    @Operation(summary = "Listar marcas.", description = "Lista marcas.")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/list-brands")
	public ResponseEntity<List<BrandResponseDTO>> listBrands(Pageable pageable) {
		Page<Brand> page = brandService.listBrands(pageable);

		return ResponseEntity.status(HttpStatus.OK).body(page.map(brandMapper::toBrandResponseDTO).getContent());
	}
}