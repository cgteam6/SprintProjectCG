package com.cg.oam.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;

//import org.hibernate.annotations.common.util.impl.LoggerFactory;

//import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


import com.cg.oam.entities.Customer;
import com.cg.oam.exception.CustomerNotFoundException;
import com.cg.oam.repository.CustomerRepository;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
@Autowired
	CustomerRepository repository;

//Logger logger = (Logger) LoggerFactory.logger(CustomerServiceImpl.class);

	@Override
	public Customer addCustomer(Customer customer) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
		String encodedPassword = bCryptPasswordEncoder.encode(customer.getCustomerPassword());
		customer.setCustomerPassword(encodedPassword);
		Customer customerData = repository.save(customer);
	//	logger.info("*** Service : Customer added successfully. ***");
		return customerData;
	}

	@Override
	public Customer updateCustomer(Customer customer) throws CustomerNotFoundException {
		// TODO Auto-generated method stub
	//	logger.info("*** Service : customer updated successfullt.***");
		return repository.save(customer);
	}

	@Override
	public Customer viewCustomer(int customerId) throws CustomerNotFoundException {
		// TODO Auto-generated method stub
		Customer customer = repository.findById(customerId).orElse(null);
	//	logger.info("*** Service : Displaying customer information.***");
		return customer;
	}

	@Override
	public Customer deleteCustomer(int customerId) throws CustomerNotFoundException {
		// TODO Auto-generated method stub
		Customer customer = repository.findById(customerId).orElse(null);
		if(repository.existsById(customerId)) {
			repository.deleteById(customerId);
	//		logger.info("*** Service : customer removed. ***");
		}
		return customer;
	}

	@Override
	public List<Customer> showAllCustomers() {
		// TODO Auto-generated method stub
		Set<Customer> customer = new HashSet<Customer>();
		List<Customer> CustomerList =  new ArrayList<Customer>(customer);
		repository.findAll().forEach(customer1->customer.add(customer1));
		return CustomerList;
	}
	
	@Override
	public Customer validateCustomer(String customerName, String customerPassword) {

		Customer customer = repository.findByCustomerName(customerName);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		if (encoder.matches(customerPassword, customer.getCustomerPassword()))
			return customer;
		else
			return null;
	}
	
}
