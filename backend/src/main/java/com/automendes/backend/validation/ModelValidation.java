package com.automendes.backend.validation;

import com.automendes.backend.entity.Model;

public interface ModelValidation {
	void validateRegisterModel(Model model);

	void validateUpdateModel(Model model);
}