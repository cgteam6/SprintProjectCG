package com.cg.oam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.oam.entities.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Integer> {
	

}
