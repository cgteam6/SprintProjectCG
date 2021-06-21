package com.cg.oam;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import com.cg.oam.*;
import com.cg.oam.entities.CartItem;
import com.cg.oam.entities.Customer;
import com.cg.oam.entities.Medicine;
import com.cg.oam.entities.Order;
import com.cg.oam.exception.OrderNotFoundException;
import com.cg.oam.repository.OrderRepository;
import com.cg.oam.service.OrderService;
import com.cg.oam.service.OrderServiceImpl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.support.DirtiesContextTestExecutionListener;
import org.springframework.test.context.transaction.TransactionalTestExecutionListener;

/**
 * Created by Suhail Parakkal.
 */
@Ignore
@RunWith(SpringRunner.class)
@SpringBootTest(classes = OnlineAyurvedaMedAppliationApplication.class)

public class OrderServiceTest {
    @Autowired
    private OrderServiceImpl orderServiceImpl;
    @MockBean
    private OrderRepository orderRepository;
	@Test
    public void cancelOrder() throws OrderNotFoundException {
	    Medicine ashwagandha = new Medicine(101, "Ashwagandha", 50, LocalDate.of(2019, 02, 20),
				LocalDate.of(2020, 02, 20), "Oushadhi");
		CartItem item1 = new CartItem(101, ashwagandha, 10);
		List<CartItem> itemList = new ArrayList<>();
		itemList.add(item1);
		Customer customer = new Customer(101, "Suhail", "suhail10");
		Order order = new Order(101, LocalDate.of(2021, 8, 12), LocalDate.of(2021, 7, 12), 1230, customer, itemList) ;
        when(orderRepository.save(order)).thenReturn(order);
        orderServiceImpl.cancelOrder(order);
        try{
        	if(orderServiceImpl.showOrder(order.getOrderId()) == order) {
        		System.out.println("***Order is not cancelled. Test case failed!!!!***");
        }
        }
        catch(OrderNotFoundException ex) {
        	System.out.println("****Order is not found. Hence cancelled. Test case passed Successfully!!!!****");
        }
		
    }
	
	@Test
    public void showAllOrders() throws OrderNotFoundException {
	    Medicine ashwagandha = new Medicine(101, "Ashwagandha", 50, LocalDate.of(2019, 02, 20),
				LocalDate.of(2020, 02, 20), "Oushadhi");
	    Medicine brahmi = new Medicine(102, "Brahmi", 50, LocalDate.of(2019, 02, 20), LocalDate.of(2020, 02, 20),
				"Oushadhi");
		CartItem item1 = new CartItem(105, ashwagandha, 10);
		CartItem item2 = new CartItem(108, brahmi, 12);
		List<CartItem> itemList = new ArrayList<>();
		itemList.add(item1);
		Customer customer = new Customer(101, "Suhail", "suhail10");
		Order order1 = new Order(101, LocalDate.of(2021, 8, 12), LocalDate.of(2021, 7, 12), 1230, customer, itemList) ;
		Order order2 = new Order(102, LocalDate.of(2021, 10, 7), LocalDate.of(2021, 11, 8), 850, customer, itemList) ;
        when(orderRepository.save(order1)).thenReturn(order1);
        when(orderRepository.save(order1)).thenReturn(order1);
        orderServiceImpl.showAllOrders(customer);
        try{
        	List<Order> orderList = orderServiceImpl.showAllOrders(customer);
        	
        	for (Order order : orderList) {
        		if(order == order1 || order == order2) {
        			System.out.println("***Orders found. Test cases passed***");
        		}
        	}
        		
        
        }
        catch(OrderNotFoundException ex) {
        	System.out.println("****Orders are not found. Test case failed!!!!****");
        }
		
    }
}