package com.cg.oam.controller;
import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cg.oam.entities.Cart;
import com.cg.oam.entities.CartItem;
import com.cg.oam.entities.Customer;
import com.cg.oam.entities.Order;
import com.cg.oam.exception.OrderNotFoundException;
import com.cg.oam.service.CartItemService;
import com.cg.oam.service.CartItemServiceImpl;
import com.cg.oam.service.OrderService;
import com.cg.oam.service.OrderServiceImpl;


@RestController
public class OrderController{

	@Autowired
	CartItemServiceImpl cartItemServiceImpl;
	
	@Autowired
	OrderServiceImpl orderServiceImpl;
	
	
	
//Suhail Parakkal - Start	 
	
	
	
	@RequestMapping(value = "/order/{cartId}", method = RequestMethod.POST)
	public String saveOrder(@PathVariable("cartId")Integer cartId){
		Cart cart = new Cart();
		cart.setCartId(cartId);
		Order order = new Order();
			
		Customer customer=cart.getCustomer();
		order.setCustomer(customer);
		order.setCart(cart);
		
		//customerOrderService.addCustomerOrder(customerOrder);
		return "redirect:/checkout?cartId="+cartId;
	}
	
	
	
	@DeleteMapping("/delete")
	public void cancelorder(@PathVariable("orderId") int orderId) {
		try {
		Order myOrder = orderServiceImpl.showOrder(orderId);
		orderServiceImpl.cancelOrder(myOrder);
		}
		
		catch(OrderNotFoundException ex) {
			System.out.print("Order not found\n");
		}
	}
	
	
	@GetMapping("/{orderId}")
	public ResponseEntity<Object> getAllOrders(@PathVariable("orderId") Integer orderId){
		try {
			Order order = orderServiceImpl.showOrder(orderId);
			return new ResponseEntity<>(order, HttpStatus.OK);
		}
		catch(OrderNotFoundException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
		
	}
	
	@GetMapping("/customerId")
	public ResponseEntity<List<Order>> getAllorders(@RequestParam("customerId") Integer customerId){
		Customer customer = new Customer();
		customer.setCustomerId(customerId);
		

		try {
			List<Order> orderList = orderServiceImpl.showAllOrders(customer);
			return new ResponseEntity<List<Order>>(orderList, HttpStatus.OK);
		}
		catch(OrderNotFoundException e) {
			return new ResponseEntity<List<Order>>(HttpStatus.NOT_FOUND);
		}
}
	
    @GetMapping("/orderDate")
    public ResponseEntity<List<Order>> getAllOrders(@RequestParam("orderDate") LocalDate orderDate){

		try {
			List<Order> orderList = orderServiceImpl.showAllOrders(orderDate);
			return new ResponseEntity<List<Order>>(orderList, HttpStatus.OK);
		}
		catch(OrderNotFoundException e) {
			return new ResponseEntity<List<Order>>(HttpStatus.NOT_FOUND);
		}
}

//Suhail - end


//Ravikumar - Start

//To place an order


@PostMapping("/addorder")
public ResponseEntity<String> addOrder( @RequestBody Order Orderentity)
{
	String result = null;
	boolean status = false;
	try {
		status = orderServiceImpl.addOrder(Orderentity);
	} catch (OrderNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	if(status)
	{	
		result = "Order Placed";
		return new ResponseEntity<String>(result,HttpStatus.OK);
	}
	else
	{
		result = "Unsuccessfull";
		return new ResponseEntity<String>(result,HttpStatus.BAD_REQUEST);
		
	}
}

//to get order by id
	@GetMapping("/getorder/{id}")
	public ResponseEntity<Order> getProductbyId(@PathVariable int id) 
	{
		Order order = null;
		try {
			order = orderServiceImpl.viewOrder(id);
		} catch (OrderNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ResponseEntity<Order>(order,HttpStatus.OK);
	}

	//To update an order
	@PostMapping("/updateorder")
	public ResponseEntity<String> updateOrder( @RequestBody Order Orderentity)
	{
		String result = null;
		boolean status = false;
		try {
			status = orderServiceImpl.updateOrder(Orderentity);
		} catch (OrderNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(status)
		{	
			result = "Order Updated";
			return new ResponseEntity<String>(result,HttpStatus.OK);
		}
		else
		{
			result = "Unsuccessfull";
			return new ResponseEntity<String>(result,HttpStatus.BAD_REQUEST);
			
		}
	}
	//Ravikumar- end
}








