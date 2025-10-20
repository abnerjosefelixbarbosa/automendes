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

import com.automendes.backend.dto.ModelRequestDTO;
import com.automendes.backend.dto.ModelResposeDTO;
import com.automendes.backend.entity.Model;
import com.automendes.backend.mapper.ModelMapper;
import com.automendes.backend.service.ModelService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/models")
public class ModelController {
	@Autowired
	private ModelService modelService;
	@Autowired
	private ModelMapper modelMapper;

	@ApiResponses({
		@ApiResponse(responseCode = "201", description = "Registra um modelo."),
		@ApiResponse(responseCode = "400", description = "Retorna um erro de requisição."),
		@ApiResponse(responseCode = "404", description = "Retorna um erro de recurso não encontrado."),
    })
    @Operation(summary = "Registrar modelo.", description = "Registra um modelo.")
	@ResponseStatus(HttpStatus.CREATED)
	@PostMapping(value = "/register-model")
	public ResponseEntity<ModelResposeDTO> registerModel(@Valid @RequestBody ModelRequestDTO dto) {
		Model model = modelService.registerModel(modelMapper.toModel(dto));

		return ResponseEntity.status(HttpStatus.CREATED).body(modelMapper.toModelResposeDTO(model));
	}

	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Atualiza um modelo."),
		@ApiResponse(responseCode = "400", description = "Retorna um erro de requisição."),
		@ApiResponse(responseCode = "404", description = "Retorna um erro de recurso não encontrado."),
    })
    @Operation(summary = "Atualizar modelo pelo id.", description = "Atualiza um modelo pelo id.")
	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/update-model-by-id")
	public ResponseEntity<ModelResposeDTO> updateModelById(@RequestParam String id,
			@Valid @RequestBody ModelRequestDTO dto) {
		Model model = modelService.updateModelById(id, modelMapper.toModel(dto));

		return ResponseEntity.status(HttpStatus.OK).body(modelMapper.toModelResposeDTO(model));
	}
	
	@ApiResponses({
		@ApiResponse(responseCode = "200", description = "Lista modelos."),
		@ApiResponse(responseCode = "400", description = "Retorna um erro de requisição."),
		@ApiResponse(responseCode = "404", description = "Retorna um erro de recurso não encontrado."),
    })
    @Operation(summary = "Listar modelos.", description = "Lista modelos.")
	@ResponseStatus(HttpStatus.OK)
	@GetMapping(value = "/list-models")
	public ResponseEntity<List<ModelResposeDTO>> listModels(Pageable pageable) {
		Page<Model> page = modelService.listModels(pageable);

		return ResponseEntity.status(HttpStatus.OK).body(page.map(modelMapper::toModelResposeDTO).getContent());
	}
}