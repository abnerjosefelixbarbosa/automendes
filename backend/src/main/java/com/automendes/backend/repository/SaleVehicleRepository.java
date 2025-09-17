package com.automendes.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automendes.backend.entity.SaleVehicle;

public interface SaleVehicleRepository extends JpaRepository<SaleVehicle, String> {

}