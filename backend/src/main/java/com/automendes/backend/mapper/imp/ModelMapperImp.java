package com.automendes.backend.mapper.imp;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.automendes.backend.dto.ModelRequestDTO;
import com.automendes.backend.dto.ModelResposeDTO;
import com.automendes.backend.entity.Brand;
import com.automendes.backend.entity.Model;
import com.automendes.backend.mapper.ModelMapper;

@Component
public class ModelMapperImp implements ModelMapper {
	public Model toModel(ModelRequestDTO dto) {
		Model model = new Model();
		
		Brand brand = new Brand();
		
		BeanUtils.copyProperties(dto, model);
		
		brand.setName(dto.getBrandName());
		
		model.setBrand(brand);
		
		return model;
	}

	public ModelResposeDTO toModelResposeDTO(Model entity) {
		ModelResposeDTO modelResposeDTO = new ModelResposeDTO();
		
		Brand brand = new Brand();
		
		BeanUtils.copyProperties(entity, modelResposeDTO);
		
		brand.setName(entity.getBrand().getName());
		
		modelResposeDTO.setBrandName(brand.getName());
		
		return modelResposeDTO;
	}
}