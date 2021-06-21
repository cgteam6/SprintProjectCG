package com.cg.oam.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
@Entity
@Table(name="carttable")
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer cartId;
	@OneToOne
	Customer customer;
	@OneToMany(mappedBy = "carts",cascade = CascadeType.ALL)
	
	List<CartItem> items;
	
	
	public Cart() {
	}

	public Cart(Integer cartId, Customer customer, List<CartItem> items) {
		this.cartId = cartId;
		this.customer = customer;
		this.items = items;
	}

	public int getCartId() {
		return this.cartId;
	}

	public void setCartId(Integer cartId) {
		this.cartId = cartId;
	}

	public Customer getCustomer() {
		return this.customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<CartItem> getItems() {
		return this.items;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}
}