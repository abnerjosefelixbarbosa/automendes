package com.automendes.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automendes.backend.entity.Vehicle;

public interface VehicleRepository extends JpaRepository<Vehicle, String> {

}