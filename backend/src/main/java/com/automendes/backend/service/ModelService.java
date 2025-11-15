package com.automendes.backend.service;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.automendes.backend.entity.Model;

public interface ModelService {
	Model registerModel(Model model);

	Model updateModelById(String id, Model model);

	Page<Model> listModels(Pageable pageable);

	Model searchModelById(String id);
	
	Optional<Model> findByName(String name);
}