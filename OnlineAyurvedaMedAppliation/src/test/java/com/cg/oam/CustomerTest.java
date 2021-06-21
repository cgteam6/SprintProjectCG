package com.cg.oam;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.cg.oam.entities.Customer;
import com.cg.oam.entities.Medicine;
import com.cg.oam.repository.CustomerRepository;
import com.cg.oam.service.CustomerService;
@SpringBootTest
public class CustomerTest {

	@Autowired
	CustomerService customerService;
	@MockBean
	CustomerRepository customerRepository;
	
	@Test
	void testAddCustomer() {
		Customer customer =  getCustomer();
		when(customerRepository.save(customer)).thenReturn(customer);
		assertEquals(customerService.addCustomer(customer), customer);
	//	fail("Not yet implemented");
	}

	@Test
	void testUpdateCustomer() {
		Customer customer = getCustomer();
		when(customerRepository.save(customer)).thenReturn(customer);
		assertEquals(customerService.updateCustomer(customer), customer);
		
	//	fail("Not yet implemented");
	}

	

	@Test
	void testDeleteCustomer() {
		Customer customer = getCustomer();
		customerService.deleteCustomer(customer.getCustomerId());
		verify(customerRepository, times(1)).existsById(customer.getCustomerId());

	//	fail("Not yet implemented");
	}
	@Test
	public void testGetCustomer() {
		
		Customer customer = getCustomer();
		customerService.viewCustomer(customer.getCustomerId());
		verify(customerRepository, times(1)).findById(customer.getCustomerId());
	}

	
	private Customer getCustomer() {
		Customer customer=new Customer();

		customer.setCustomerId(5);
		customer.setCustomerName("Aadi");
		

		return customer;
	}

}
