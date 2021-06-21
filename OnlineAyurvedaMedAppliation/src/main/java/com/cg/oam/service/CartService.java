package com.cg.oam.service;

import com.cg.oam.entities.Customer;
import com.cg.oam.entities.Cart;
import com.cg.oam.entities.CartItem;

public interface CartService{

    public boolean createCart(Customer customer);

    public boolean removeCart(Cart cart);

    public boolean clearCart(Cart cart);

    public void deleteCartItem(CartItem cartItem);

    }
