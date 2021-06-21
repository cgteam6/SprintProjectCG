package com.cg.oam.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.oam.entities.Customer;
import com.cg.oam.entities.Cart;
import com.cg.oam.entities.CartItem;
import com.cg.oam.exception.CartException;
import com.cg.oam.service.CartService;
@RestController
@RequestMapping("/cart")
public class CartController {

	@Autowired
	CartService service;

	Logger logger = LoggerFactory.getLogger(CartController.class);

	// Create Cart
	@PostMapping("/createCart")
	public ResponseEntity<Boolean> createCart(@Valid @RequestBody Customer customer) {
		Boolean cartData = service.createCart(customer);

		if (cartData == false) {
			logger.error("Controller: Failed to create cart");
			CartException cartException = new CartException("Cart was not created");
					}
		logger.info("*** Controller : Cart created successfully. ***");
		return new ResponseEntity<Boolean>(cartData, HttpStatus.OK);
	}

        // Remove Cart
	@DeleteMapping("/removeCart")
	public ResponseEntity<Boolean> removeCart(@Valid @RequestBody Cart cart) {
		Boolean cartData = service.removeCart(cart);

		if (cartData == false) {
			logger.error("Controller: Failed to remove cart");
			CartException cartException = new CartException("Cart was not removed");
		}
		logger.info("*** Controller : Cart removed successfully. ***");
		return new ResponseEntity<Boolean>(cartData, HttpStatus.OK);
         }

         // Clear Cart
	@DeleteMapping("/clearCart")
	public ResponseEntity<Boolean> clearCart(@Valid @RequestBody Cart cart) {
		Boolean cartData = service.clearCart(cart);

		if (cartData == false) {
			logger.error("Controller: Failed to clear cart");
			CartException cartException = new CartException("Cart was not cleared");
		}
		logger.info("*** Controller : Cart cleared successfully. ***");
		return new ResponseEntity<Boolean>(cartData, HttpStatus.OK);
	}

}