package com.automendes.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automendes.backend.entity.Model;

public interface ModelRepository extends JpaRepository<Model, String> {
	boolean existsByName(String name);

	Optional<Model> findByName(String name);
}