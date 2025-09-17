package com.automendes.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automendes.backend.entity.Model;

public interface ModelRepository extends JpaRepository<Model, String> {

}
