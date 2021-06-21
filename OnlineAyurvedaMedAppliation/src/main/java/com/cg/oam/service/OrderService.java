package com.cg.oam.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.oam.entities.Customer;
import com.cg.oam.entities.Order;
import com.cg.oam.exception.OrderNotFoundException;

public interface OrderService  extends JpaRepository<Order, Integer>{
	public boolean addOrder(Order Orderentity) throws OrderNotFoundException;

	public Order viewOrder(int OrderId) throws OrderNotFoundException;

	public boolean updateOrder(Order Orderentity) throws OrderNotFoundException;

	public Order cancelOrder(int var1) throws OrderNotFoundException;

	public Order showOrder(int var1) throws OrderNotFoundException;

	public List<Order> showAllOrders(Customer var1) throws OrderNotFoundException;

	public List<Order> showAllOrders(LocalDate var1) throws OrderNotFoundException;



}
