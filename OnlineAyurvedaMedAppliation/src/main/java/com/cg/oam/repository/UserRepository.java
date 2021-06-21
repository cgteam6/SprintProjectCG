package com.cg.oam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.oam.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	
}

