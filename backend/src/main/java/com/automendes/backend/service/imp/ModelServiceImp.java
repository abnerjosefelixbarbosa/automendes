package com.automendes.backend.service.imp;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.automendes.backend.entity.Brand;
import com.automendes.backend.entity.Model;
import com.automendes.backend.exception.NotFoundException;
import com.automendes.backend.repository.BrandRepository;
import com.automendes.backend.repository.ModelRepository;
import com.automendes.backend.service.ModelService;
import com.automendes.backend.validation.ModelValidation;
import com.fasterxml.uuid.Generators;

import jakarta.transaction.Transactional;

@Service
public class ModelServiceImp implements ModelService {
	@Autowired
	private ModelRepository modelRepository;
	@Autowired
	private BrandRepository brandRepository;
	@Autowired
	private ModelValidation modelValidation; 

	@Transactional
	public Model registerModel(Model model) {
		modelValidation.validateModel(model);
		
		model.setId(Generators.timeBasedEpochRandomGenerator().generate().toString());
		
        Brand brand = brandRepository.findByName(model.getBrand().getName())
        		.orElseThrow(() -> new NotFoundException("Nome da marca deve ser existente.")); 
		
		model.setBrand(brand);
		
		return modelRepository.save(model);
	}

	@Transactional
	public Model updateModelById(String id, Model model) {
		modelValidation.validateModel(model);
		
		Brand brand = brandRepository.findByName(model.getBrand().getName())
        		.orElseThrow(() -> new NotFoundException("Nome da marca deve ser existente.")); 
        
		model.setBrand(brand);
		
		Model modelFound = modelRepository.findById(id)
				.orElseThrow(() -> new NotFoundException("Id deve ser existente."));
		
		BeanUtils.copyProperties(model, modelFound, "id");
		
		return modelRepository.save(modelFound);
	}

	public Page<Model> listModels(Pageable pageable) {
		return modelRepository.findAll(pageable);
	}
}