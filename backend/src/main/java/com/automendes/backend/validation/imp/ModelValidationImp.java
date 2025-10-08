package com.automendes.backend.validation.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.automendes.backend.entity.Model;
import com.automendes.backend.repository.ModelRepository;
import com.automendes.backend.validation.ModelValidation;

@Component
public class ModelValidationImp implements ModelValidation {
	@Autowired
	private ModelRepository modelRepository;

	public void validateModel(Model model) {
		if (modelRepository.existsByName(model.getName())) {
			throw new RuntimeException("Nome deve ser único.");
		}
	}
}
