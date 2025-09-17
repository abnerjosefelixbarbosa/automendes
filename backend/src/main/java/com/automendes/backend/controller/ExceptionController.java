package com.automendes.backend.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.automendes.backend.dto.ExceptionDTO;

import jakarta.servlet.http.HttpServletRequest;

@RestControllerAdvice
public class ExceptionController {
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		
		return errors;
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(RuntimeException.class)
	public ResponseEntity<ExceptionDTO> handleRuntimeException(RuntimeException e, HttpServletRequest request) {
		ExceptionDTO exceptionDTO = new ExceptionDTO(LocalDateTime.now(), 400, e.getMessage(),
				request.getRequestURI());
		
		return ResponseEntity.status(400).body(exceptionDTO);
	}

	@ResponseStatus(HttpStatus.NOT_FOUND)
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<ExceptionDTO> handleNotFoundException(NotFoundException e, HttpServletRequest request) {
		ExceptionDTO exceptionDTO = new ExceptionDTO(LocalDateTime.now(), 404, e.getMessage(),
				request.getRequestURI());
		
		return ResponseEntity.status(404).body(exceptionDTO);
	}
}