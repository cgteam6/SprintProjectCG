package com.cg.oam;

import java.io.*;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.oam.entities.Customer;
import com.cg.oam.entities.Cart;
import com.cg.oam.entities.CartItem;
import com.cg.oam.entities.Medicine;
import com.cg.oam.repository.CustomerRepository;
import com.cg.oam.repository.CartRepository;
import com.cg.oam.repository.CartItemRepository;
import com.cg.oam.repository.MedicineRepository;
import com.cg.oam.service.CustomerService;
import com.cg.oam.service.CartService;
import com.cg.oam.service.CartItemService;
import com.cg.oam.service.MedicineService;

@SpringBootTest
public class CartTest{

        @Autowired
	CartService cartService;
	
	@MockBean
	CartRepository cartRepo;

        @Test
	public void testCreateCart(){

                Customer customer = getCustomer();
                Cart cart = getCart();
		cartService.createCart(customer);
                assertEquals(true , cartRepo.existsById(cart.getCartId()));

        }

        @Test
        public void testRemoveCart(){

              Cart cart = getCart();
              cartService.removeCart(cart);
              assertEquals(false , cartRepo.existsById(cart.getCartId()));
        
              }

        @Test
        public void testClearCart(){

              Cart cart = getCart();
              cartService.clearCart(cart);
              List<CartItem> cartItems = cart.getItems();
              assertEquals(true , cartItems.isEmpty());

              }

        private Cart getCart(){
              Cart cart = new Cart();
               
              cart.setCartId(1);
              cart.setCustomer(getCustomer());
              cart.setItems((List<CartItem>) getCartItem());
              return cart;
              }

        private Customer getCustomer(){
              Customer customer = new Customer();

              customer.setCustomerId(1);
              customer.setCustomerName("Dayana");
              customer.setCustomerPassword("dayana123");
              return customer;
              }

        private CartItem getCartItem(){
              CartItem cartItem = new CartItem();

              cartItem.setItemId(1);
              cartItem.setQuantity(2);
              cartItem.setCart(getCart());
              cartItem.setMedicine(getMedicine());
              return cartItem;
              }
 
        public Medicine getMedicine(){
              Medicine medicine = new Medicine();

              medicine.setMedicineId(1);
              medicine.setMedicineName("zxcvbnm");
              medicine.setMedicineCost(90);
              medicine.setMfd(LocalDate.of(2022, 02, 10));
              medicine.setCompanyName("cube");
              return medicine;
              }

        }

	