package com.cg.oam.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cg.oam.entities.CartItem;
import com.cg.oam.entities.Customer;
import com.cg.oam.entities.Order;
import com.cg.oam.entities.User;
import com.cg.oam.exception.OrderNotFoundException;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer>{
	List<Order> findAllByUserIdOrderByCreatedDateDesc(Integer userId);

	public boolean addOrder(Order Orderentity);
	public Order viewOrder(int OrderId);
	public boolean updateOrder(Order Orderentity);
}
