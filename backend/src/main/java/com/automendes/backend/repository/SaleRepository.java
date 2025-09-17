package com.automendes.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.automendes.backend.entity.Sale;

public interface SaleRepository extends JpaRepository<Sale, String> {

}
