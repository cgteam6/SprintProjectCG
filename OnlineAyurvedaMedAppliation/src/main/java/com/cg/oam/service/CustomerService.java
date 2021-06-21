package com.cg.oam.service;

import java.util.List;
import com.cg.oam.entities.Customer;
import com.cg.oam.exception.CustomerNotFoundException;

public interface CustomerService {

	Customer addCustomer(Customer var1);

	Customer updateCustomer(Customer var1) throws CustomerNotFoundException;

	Customer viewCustomer(int var1) throws CustomerNotFoundException;

	Customer deleteCustomer(int var1) throws CustomerNotFoundException;
	List<Customer> showAllCustomers();
	
	public Customer validateCustomer(String customerName, String customerPassword);
}
