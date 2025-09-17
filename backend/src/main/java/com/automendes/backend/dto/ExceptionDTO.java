package com.automendes.backend.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public record ExceptionDTO(
		@JsonFormat(pattern = "yyyy-MM-dd HH:ss")
		LocalDateTime localDateTime,
		Integer status,
		String message,
		String path
) {}