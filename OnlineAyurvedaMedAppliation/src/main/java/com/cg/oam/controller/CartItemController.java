package com.cg.oam.controller;

import java.time.LocalDate;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cg.oam.entities.Cart;
import com.cg.oam.entities.CartItem;
import com.cg.oam.entities.Medicine;
import com.cg.oam.exception.EmptyCartException;
import com.cg.oam.exception.ItemNotFoundException;
import com.cg.oam.service.CartItemService;
import com.cg.oam.service.CartService;
import com.cg.oam.service.MedicineService;

@RestController
@RequestMapping("/cartItem")
public class CartItemController {
	
	@Autowired
	CartItemService cartItemSer;
	@Autowired
	CartService cartService;
	@Autowired
	MedicineService medicineService;
	
	@PostMapping("/addItem")
	public ResponseEntity<CartItem> addItemToCart(@Valid @RequestBody Cart cart,@Valid @RequestBody Medicine medicine) {
		CartItem cartItem = cartItemSer.addItemToCart(cart, medicine);
		return new ResponseEntity<CartItem>(cartItem,HttpStatus.OK);
		
	}
	@PutMapping("/decreseQuantity")
	public ResponseEntity<CartItem> decreaseQuantity(@Valid @RequestBody Cart cart,@Valid @RequestBody Medicine medicine) throws ItemNotFoundException {
		try {
			CartItem cartItem = cartItemSer.decreaseQuantity(cart, medicine);
			return new ResponseEntity<CartItem>(cartItem,HttpStatus.OK);
		}catch(Exception e) {
			throw new ItemNotFoundException("Item not Found");
		}
		
	}
	@GetMapping
	public ResponseEntity<List<CartItem>> getAllItems(@Valid @RequestBody Cart cart) throws EmptyCartException{
		try {
			List<CartItem> items = cartItemSer.getAllCartItem(cart);
			return new ResponseEntity<List<CartItem>>(items,HttpStatus.OK);
		}catch (Exception e) {
			// TODO: handle exception
			throw new EmptyCartException("Cart is Empty");
		}
		
	}
	

}
