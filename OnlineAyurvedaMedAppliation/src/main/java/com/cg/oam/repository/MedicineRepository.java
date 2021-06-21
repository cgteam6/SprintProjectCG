package com.cg.oam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.oam.entities.Medicine;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Integer> {
	
}