package com.cg.oam.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cg.oam.entities.Cart;
import com.cg.oam.entities.CartItem;
import com.cg.oam.entities.Medicine;
import com.cg.oam.exception.ItemNotFoundException;
import com.cg.oam.exception.MedicineNotFoundException;

public interface CartItemService {
	 public  CartItem addItemToCart(Cart cart,Medicine medicine);
	 public  CartItem decreaseQuantity(Cart cart, Medicine medicine) throws ItemNotFoundException;
	 public List<CartItem> getAllCartItem(Cart cart);
	
	 
	 
	

}
