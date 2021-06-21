package com.cg.oam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.oam.entities.Cart;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer>{

}
