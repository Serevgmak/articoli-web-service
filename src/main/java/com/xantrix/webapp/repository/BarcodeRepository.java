package com.xantrix.webapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.xantrix.webapp.entities.Barcode;

public interface BarcodeRepository extends JpaRepository<Barcode, Long>{

    Barcode findByBarcode(String barcode);
    
}
