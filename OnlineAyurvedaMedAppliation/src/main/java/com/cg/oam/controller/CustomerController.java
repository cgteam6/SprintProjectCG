package com.cg.oam.controller;

import java.util.List;

import javax.validation.Valid;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.cg.oam.entities.Customer;
import com.cg.oam.exception.CustomerNotFoundException;
import com.cg.oam.service.CustomerService;

@RequestMapping("onlineayurveda/customer")
@RestController
public class CustomerController {
	// autowire the Customer Service class....
	@Autowired
		CustomerService customerService;
	
	
	//method to add customer details..
	
	/*@PostMapping("/customerDetails/{customerId}")
	public Customer addCustomer(Customer customerId) {
		return customerService.addCustomer(customerId);
	}*/
	@PostMapping("/addCustomer")
	public ResponseEntity<Customer> addCustomer(@Valid @RequestBody Customer customer) {
		Customer customerData = customerService.addCustomer(customer);
        if(customerData==null)
        {
        
        	throw new CustomerNotFoundException("Customer not added");
        }
	
    
		return new ResponseEntity<Customer>(customerData, HttpStatus.OK);
	}

	// retrieving all customer details....
	@GetMapping("/viewAllCustomers")
	public List<Customer>showAllCustomers(){
		return customerService.showAllCustomers();
	}

	//retrieving specific customer details....
	
	/*@GetMapping("customerDetails/{customerId}")
	public Customer viewCustomer(@PathVariable("customerId")int customerId) throws CustomerNotFoundException {
		return customerService.viewCustomer(customerId);
	}*/
	
	@GetMapping("/viewCustomer/{customerId}")
	public ResponseEntity<Customer> viewCustomer(@PathVariable int customerId) {
		Customer customerData = customerService.viewCustomer(customerId);
		
		 if (customerData == null) { 
			
			 throw new CustomerNotFoundException("No Customer for given Id "+customerId );
			 }
		
		return new ResponseEntity<Customer>(customerData, HttpStatus.OK);
	}

	// updating the customer details....
	
	/*public Customer updateCustomer(@RequestBody Customer customer) throws CustomerNotFoundException {
		customerService.updateCustomer(customer);
		return customer; 
	} */
	@PutMapping("/updateCustomer/{customerId}")
	public ResponseEntity<Customer> updateCustomer(@Valid @RequestBody Customer customer) {
		Customer customerData = customerService.updateCustomer(customer);
		 if(customerData==null)
	        {
		
			 throw new CustomerNotFoundException("Customer not updated");
	        }
		
	
		return new ResponseEntity<Customer>(customerData, HttpStatus.OK);
	}

	//deleting a specific customer....
	/*@DeleteMapping("/customerDetails/{customerId}")
	public  Customer deleteCustomer(@PathVariable("customerId") int customerId) throws CustomerNotFoundException {
		customerService.deleteCustomer(customerId);
		return deleteCustomer(customerId);*/
	
	@DeleteMapping("/deleteCustomer/{customerId}")
	public ResponseEntity<Customer> deleteCustomer(@PathVariable int customerId) {

		Customer customerData = customerService.deleteCustomer(customerId);
		 if (customerData == null) { 
		
			 throw new CustomerNotFoundException("No Customer is found for given Id "+customerId );
			 }
		return new ResponseEntity<Customer>(customerData, HttpStatus.OK);
	}
	
	
	
	
	}


