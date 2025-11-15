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

import com.automendes.backend.dto.ModelRequestDTO;
import com.automendes.backend.dto.ModelResposeDTO;
import com.automendes.backend.entity.Model;
import com.automendes.backend.mapper.ModelMapper;
import com.automendes.backend.service.ModelService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/models")
public class ModelController {
	private ModelService modelService;
	private ModelMapper modelMapper;
	
	public ModelController(ModelService modelService, ModelMapper modelMapper) {
		this.modelService = modelService;
		this.modelMapper = modelMapper;
	}

	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/register-model")
	public ResponseEntity<ModelResposeDTO> registerModel(@Valid @RequestBody ModelRequestDTO dto) {
		Model model = modelService.registerModel(modelMapper.toModel(dto));

		return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.toModelResposeDTO(model));
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/update-model-by-id")
	public ResponseEntity<ModelResposeDTO> updateModelById(@RequestParam String id,
			@Valid @RequestBody ModelRequestDTO dto) {
		Model model = modelService.updateModelById(id, modelMapper.toModel(dto));

		return ResponseEntity.status(HttpStatus.OK).body(modelMapper.toModelResposeDTO(model));
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/list-models")
	public ResponseEntity<List<ModelResposeDTO>> listModels(Pageable pageable) {
		Page<Model> page = modelService.listModels(pageable);

		return ResponseEntity.status(HttpStatus.OK).body(page.map(modelMapper::toModelResposeDTO).getContent());
	}
	
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/search-model-by-id")
	public ResponseEntity<ModelResposeDTO> searchModelById(@RequestParam String id) {
		Model model = modelService.searchModelById(id);

		return ResponseEntity.status(HttpStatus.OK).body(modelMapper.toModelResposeDTO(model));
	}
}