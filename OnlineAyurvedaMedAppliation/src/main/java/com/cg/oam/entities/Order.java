package com.cg.oam.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;

import javax.persistence.OneToOne;
import javax.persistence.Table;


@Entity
@Table(name="ordertable")
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer orderId;
	LocalDate orderDate;
	LocalDate dispatchDate;
	float totalCost;
	Integer userId;


	@OneToOne
	@JoinColumn(name="cartId")
	private Cart cart;
	
	@OneToOne
	Customer customer;
	
	@ManyToMany(cascade={CascadeType.ALL})@JoinTable(name="ORDER_ITEM", 
			joinColumns=@JoinColumn(name="orderId"),
			inverseJoinColumns=@JoinColumn(name="itemId"))
	List<CartItem> itemList;
	
	//No-Argument Constructor
	public Order() {
		itemList = new ArrayList<CartItem>();
	}
	
	//Constructor with arguments
	public Order(Integer orderId, LocalDate orderDate, LocalDate dispatchDate, float totalCost,
			Customer customer, List<CartItem> items) {
		super();
		this.orderId = orderId;
		this.orderDate = orderDate;
		this.dispatchDate = dispatchDate;
		this.totalCost = totalCost;
		this.customer = customer;
	}
	public Order(Integer orderId, List<CartItem> items) {
		this.orderId = orderId;
		
	}
	
	
	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Cart getCart() {
		return cart;
	}

	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public List<CartItem> getItemList() {
		return itemList;
	}

	public void setItemList(List<CartItem> itemList) {
		this.itemList = itemList;
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(LocalDate orderDate) {
		this.orderDate = orderDate;
	}

	public LocalDate getDispatchDate() {
		return dispatchDate;
	}

	public void setDispatchDate(final LocalDate dispatchDate) {
		this.dispatchDate = dispatchDate;
	}

	public float getTotalCost() {
		return totalCost;
	}

	public void setTotalCost(float totalCost) {
		this.totalCost = totalCost;
	}


	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId + ", orderDate=" + orderDate + ", dispatchDate=" + dispatchDate
				+ ", totalCost=" + totalCost + ", customer=" + customer + "]";
	}	
}