package com.automendes.backend.entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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
	@OneToMany(mappedBy = "model", fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
	private List<Vehicle> vehicles;
}
