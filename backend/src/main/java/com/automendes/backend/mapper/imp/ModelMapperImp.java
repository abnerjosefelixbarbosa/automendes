package com.automendes.backend.mapper.imp;

import org.springframework.stereotype.Component;

import com.automendes.backend.dto.ModelRequestDTO;
import com.automendes.backend.dto.ModelResposeDTO;
import com.automendes.backend.entity.Brand;
import com.automendes.backend.entity.Model;
import com.automendes.backend.mapper.ModelMapper;

@Component
public class ModelMapperImp implements ModelMapper {
	public Model toModel(ModelRequestDTO dto) {
		Brand brand = new Brand(null, dto.getBrandName(), null);

		Model model = new Model(null, dto.getName(), brand, null);

		return model;
	}

	public ModelResposeDTO toModelResposeDTO(Model entity) {
		ModelResposeDTO modelResposeDTO = new ModelResposeDTO(entity.getId(), entity.getName(),
				entity.getBrand().getName());

		return modelResposeDTO;
	}
}