package com.cg.oam.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cg.oam.entities.Customer;
import com.cg.oam.controller.CartController;
import com.cg.oam.entities.Cart;
import com.cg.oam.service.CartService;
import com.cg.oam.entities.CartItem;
import com.cg.oam.repository.CartRepository;
import com.cg.oam.repository.CartItemRepository;

public class CartException{
	Logger logger = LoggerFactory.getLogger(CartException.class);
	final long serialVersionUID = 1L;
	String message;
	
	public CartException(String message) {
		super();
		this.message = message;
		logger.info(message);
	}
    public void setMessage(String message) {
    	this.message=message;
    }
	
	public String getMessage() {
		return message;

	}
    
}