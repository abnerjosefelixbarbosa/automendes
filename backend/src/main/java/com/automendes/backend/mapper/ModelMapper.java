package com.automendes.backend.mapper;

import com.automendes.backend.dto.ModelRequestDTO;
import com.automendes.backend.dto.ModelResposeDTO;
import com.automendes.backend.entity.Model;

public interface ModelMapper {
	Model toModel(ModelRequestDTO dto);
	
	ModelResposeDTO toModelResposeDTO(Model entity);
}