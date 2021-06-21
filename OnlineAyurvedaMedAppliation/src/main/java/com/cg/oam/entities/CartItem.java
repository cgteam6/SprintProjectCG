package com.cg.oam.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;



@Entity
@Table(name="cartitemtable")
public class CartItem {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer itemId;
	@OneToOne(cascade = CascadeType.ALL)
	private Medicine medicine;
	int quantity;
	@ManyToOne(cascade= {CascadeType.ALL})
	@JoinTable(name = "CARTITEM_CART",joinColumns = @JoinColumn(name="itemId"),
	inverseJoinColumns =@JoinColumn(name="cartId")) 
	private Cart carts;

	

	public CartItem() {
		super();
	}


	
	

	public CartItem(Integer itemId, Medicine medicine, int quantity, Cart carts) {
		super();
		this.itemId = itemId;
		this.medicine = medicine;
		this.quantity = quantity;
		this.carts = carts;
	}

	public CartItem(Integer itemId, Medicine medicine, int quantity) {
		super();
		this.itemId = itemId;
		this.medicine = medicine;
		this.quantity = quantity;
	}




	public Cart getCarts() {
		return carts;
	}

	public void setCarts(Cart carts) {
		this.carts = carts;
	}

	public Integer getItemId() {
		return this.itemId;
	}

	public void setItemId(Integer itemId) {
		this.itemId = itemId;
	}

	public Cart getCart() {
		return this.carts;
	}

	public void setCart(Cart cart) {
		this.carts = cart;
	}

	public Medicine getMedicine() {
		return this.medicine;
	}

	public void setMedicine(Medicine medicine) {
		this.medicine = medicine;
	}

	public int getQuantity() {
		return this.quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "CartItem [itemId=" + itemId + ", cart=" + carts + ", medicine=" + medicine + ", quantity=" + quantity
				+ "]";
	}
	
	
	
}