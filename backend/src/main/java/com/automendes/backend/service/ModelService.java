package com.automendes.backend.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.automendes.backend.entity.Model;

public interface ModelService {
	Model registerModel(Model model);

	Model updateModelById(String id, Model model);

	Page<Model> listModels(Pageable pageable);

	Model searchModelById(String id);
}