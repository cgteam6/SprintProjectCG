package com.cg.oam.service;

import java.io.*;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.cg.oam.entities.Customer;
import com.cg.oam.entities.Cart;
import com.cg.oam.service.CartService;
import com.cg.oam.entities.CartItem;
import com.cg.oam.repository.CartRepository;
import com.cg.oam.repository.CartItemRepository;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

@Service

public class CartServiceImpl implements CartService{

  @Autowired
  CartRepository cartRepo;

  @Autowired 
  CartItemRepository cartItemRepo;

  Logger logger = LoggerFactory.getLogger(CartService.class);

  /* The createCart method to create a cart for each customerId */
  public boolean createCart(Customer customer){
      
      Cart cart = new Cart();

      List<Customer> customerList = new ArrayList<Customer>();

      customerList.stream().map(x -> customer.getCustomerId()).forEach(x -> {cartRepo.save(cart);});

      logger.info("*** Service :  Cart created successfully. ***");

      return true;

      }

  /* The removeCart method to remove cartId */
  public boolean removeCart(Cart cart){
 
      cartRepo.deleteById(cart.getCartId());
      
      logger.info("*** Service :  Cart removed successfully. ***");

      return true;

      }
 
   /* The clearCart method to clear all items from cart */
   public boolean clearCart(Cart cart){

       List<CartItem> cartItems = cart.getItems();

        for (CartItem item : cartItems){
           
             deleteCartItem(item);
             
             }

       logger.info("*** Service :  Cart cleared successfully. ***");

       return true;

       }
    public void deleteCartItem(CartItem cartItem) {
		
	cartItemRepo.deleteById(cartItem.getItemId());
			
	}


   }

   