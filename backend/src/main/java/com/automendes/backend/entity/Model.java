package com.automendes.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
@Entity
@Table(name = "model_tb")
public class Model {
	@Id
	private String id;
	@Column(nullable = false, unique = true, length = 30)
	private String name;
	@ManyToOne
	@JoinColumn(nullable = false, name = "brand_id")
	private Brand brand;
}
