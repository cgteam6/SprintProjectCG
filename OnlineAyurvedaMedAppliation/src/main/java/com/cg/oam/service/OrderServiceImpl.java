package com.cg.oam.service;

import java.time.LocalDate;
import java.util.*;

import javax.transaction.Transactional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.cg.oam.entities.Cart;
import com.cg.oam.entities.Customer;
import com.cg.oam.entities.Order;
import com.cg.oam.entities.User;
import com.cg.oam.exception.EmptyCartException;
import com.cg.oam.exception.OrderNotFoundException;
import com.cg.oam.repository.CartRepository;
import com.cg.oam.repository.OrderRepository;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

   @Value("${BASE_URL}")
    String baseURL;

    @Value("${STRIPE_SECRET_KEY}")
    String apiKey;
  
    @Autowired
    CartRepository cartRepository;
    
    
    
    //RaviKumar - Start
    
	/*
	 * This method is used to add Order into the orderDetails Table.
	 * Method 	 : addOrder
	 * Type 	 : Boolean
	 * parameters: Orderentity 
	 * Author 	 : Ravi Kumar
	 * Date 	 : 17/06/2021
	 */
	@Override
	public boolean addOrder(Order Orderentity) throws OrderNotFoundException{
		boolean orderAdded = orderRepository.addOrder(Orderentity);
			if(orderAdded) {
				return orderAdded;
			}
			else {
				throw new OrderNotFoundException("Unable to add Order");
			}
		}
	
	
	
	/*
	 * This method is used to view OrderEntity from the orderDetails table based on OrderId.
	 * Method 	 : viewOrder
	 * Type 	 : Order
	 * parameters: OrderId 
	 * Author 	 : Ravi Kumar
	 * Date 	 : 17/06/2021
	 */
	@Override
	public Order viewOrder(int OrderId) throws OrderNotFoundException{
		Order order = orderRepository.viewOrder(OrderId);
		if(order != null) {
			return order;
		}
		else {
			throw new OrderNotFoundException("Order not found");
		}
	}
	
	
	/*
	 * This method is used to update Order in the orderDetails Table.
	 * Method 	 : updateOrder
	 * Type 	 : Boolean
	 * parameters: Orderentity
	 * Author 	 : Ravi Kumar
	 * Date 	 : 17/06/2021
	 */
	@Override
	public boolean updateOrder(Order Orderentity) throws OrderNotFoundException{
		boolean orderUpdated = orderRepository.updateOrder(Orderentity);
		if(orderUpdated) {
			return orderUpdated;
		}
		else {
			throw new OrderNotFoundException("Order not Updated");
		}
	}
    
    //RaviKumar - End



    
    
    //Suhail - Start
	
	//This method is used to save the order by taking user object as 
	//parameter
    public Order saveOrder(User user) throws EmptyCartException{
    	Order order = new Order();
    	List<Cart> cartList =  cartRepository.findAll();
    	Cart userCart = new Cart();
    	boolean flag = false;
    	for(Cart cart : cartList) {
    		if(cart.getCustomer().getUser().getUserName().equals(user.getUserName())) {
    			userCart = cart;
    			flag = true;
    			break;
    		}
    	}
    	if(flag)
    		throw new EmptyCartException("No items in the cart");
    	else
    		order.setItemList(userCart.getItems());
    		order.setOrderDate(LocalDate.now());
    		userCart.getItems().clear();
        return orderRepository.save(order);
    }
    
    
    
    
    //This method is used to cancel the order
    public void cancelOrder(Order order) {
    	orderRepository.deleteById(order.getOrderId());
    }
    
    
  
    //This method is used to list all orders for
    //a specific user
    public List<Order> listOrders(Integer userId) {
        List<Order> orderList = orderRepository.findAllByUserIdOrderByCreatedDateDesc(userId);
        return orderList;
    }

   
    
    // this method is used to show order using order id
    public Order showOrder(int orderId) throws OrderNotFoundException {
        Optional<Order> order = orderRepository.findById(orderId);
        if (order.isPresent()) {
        	return order.get();
        }
    else {
        throw new OrderNotFoundException("Order not found");
        }
        }
    
    
    
    //This method is used to show all orders of a specific customer
    public List<Order> showAllOrders(Customer customer) throws OrderNotFoundException {
        List<Order> orderList = orderRepository.findAll();
        List<Order> ordersFound = new ArrayList<Order>();
        
        for(Order order : orderList) {
        	if(order.getCustomer() == customer) {
        		ordersFound.add(order);
        	}
        }
        if(ordersFound.isEmpty())
        	throw new OrderNotFoundException("Order not found");
        
        else
        	return ordersFound;
        }

    
    
    // This method is used to List order according to order date
    public List<Order> showAllOrders(LocalDate orderDate) throws OrderNotFoundException {
        List<Order> ordersFound = new ArrayList<Order>();
        List<Order> orderList = orderRepository.findAll();
        for(Order order : orderList) {
        	if(order.getOrderDate() == orderDate) {
        		ordersFound.add(order);
        	}
        }
        if(ordersFound.isEmpty())
        	throw new OrderNotFoundException("Order not found");
        
        else
        	return ordersFound;
        }

//Suhail - end




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



	@Override
	public Order cancelOrder(int var1) throws OrderNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}



	
}

