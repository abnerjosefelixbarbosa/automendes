package com.automendes.backend.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automendes.backend.entity.Brand;

public interface BrandRepository extends JpaRepository<Brand, String> {
	boolean existsByName(String name);
	
	Optional<Brand> findByName(String name);
}