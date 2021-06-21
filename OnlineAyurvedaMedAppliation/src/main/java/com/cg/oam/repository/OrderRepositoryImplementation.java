package com.cg.oam.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.cg.oam.entities.Customer;
import com.cg.oam.entities.Order;
import com.cg.oam.entities.User;
import com.cg.oam.exception.OrderNotFoundException;


@Transactional
@Repository
public class OrderRepositoryImplementation implements OrderRepository {

	@PersistenceContext
	EntityManager entityManager;

	/*
	 * This method is used to add Order into the orderDetails table.
	 * Method 	 : addOrder
	 * Type 	 : Boolean
	 * parameters: Orderentity
	 * Author 	 : Ravi Kumar
	 * Date 	 : 17/06/2021
	 */
	@Override
	public boolean addOrder(Order Orderentity) {
		// TODO Auto-generated method stub
		try {
			Order order = new Order();
			order.setCustomer(Orderentity.getCustomer());
			order.setDispatchDate(Orderentity.getDispatchDate());
			order.setItemList(Orderentity.getItemList());
			order.setOrderDate(Orderentity.getOrderDate());
			order.setTotalCost(Orderentity.getTotalCost());
			Session cs = entityManager.unwrap(Session.class);
			cs.saveOrUpdate(order);
			
			return true;
			}
			catch(Exception e) {
				return false;
			}

	}
	
	
	/*
	 * This method is used to view Order from the orderDetails Table.
	 * Method 	 : viewOrder
	 * Type 	 : Order
	 * parameters: Orderentity 
	 * Author 	 : Ravi Kumar
	 * Date 	 : 17/06/2021
	 */
	@Override
	public Order viewOrder(int OrderId) {
		// TODO Auto-generated method stub
		 Order order = entityManager.find(Order.class,OrderId);
		 if(order!=null)
		 {
			 return order; 
		 }
		 return null;
	}

	
	 /* This method is used to update Order in the orderDetails Table based on OrderId.
	 * Method 	 : updateOrder
	 * Type 	 : Boolean
	 * parameters: Orderentity 
	 * Author 	 : Ravi Kumar
	 * Date 	 : 17/06/2021
	 */
	@Override
	public boolean updateOrder(Order Orderentity) {
		// TODO Auto-generated method stub
		try {
		Order order = entityManager.find(Order.class,Orderentity.getOrderId());
		order.setCustomer(Orderentity.getCustomer());
		order.setDispatchDate(Orderentity.getDispatchDate());
		order.setItemList(Orderentity.getItemList());
		order.setOrderDate(Orderentity.getOrderDate());
		order.setTotalCost(Orderentity.getTotalCost());
		Session cs = entityManager.unwrap(Session.class);
		cs.saveOrUpdate(order);
		
		return true;
		}
		catch(Exception e) {
			return false;
		}

	}



	@Override
	public List<Order> findAllByUserIdOrderByCreatedDateDesc(Integer userId) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Order> findAll() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Order> findAll(Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public List<Order> findAllById(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <S extends Order> List<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void flush() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public <S extends Order> S saveAndFlush(S entity) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <S extends Order> List<S> saveAllAndFlush(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public void deleteAllInBatch(Iterable<Order> entities) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteAllByIdInBatch(Iterable<Integer> ids) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteAllInBatch() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public Order getOne(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Order getById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <S extends Order> List<S> findAll(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <S extends Order> List<S> findAll(Example<S> example, Sort sort) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Page<Order> findAll(Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <S extends Order> S save(S entity) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public Optional<Order> findById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean existsById(Integer id) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void delete(Order entity) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteAllById(Iterable<? extends Integer> ids) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteAll(Iterable<? extends Order> entities) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public <S extends Order> Optional<S> findOne(Example<S> example) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <S extends Order> Page<S> findAll(Example<S> example, Pageable pageable) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public <S extends Order> long count(Example<S> example) {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public <S extends Order> boolean exists(Example<S> example) {
		// TODO Auto-generated method stub
		return false;
	}
}

