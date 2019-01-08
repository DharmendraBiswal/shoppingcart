package com.example.shopping_cart.service;

import java.util.List;

import com.example.shopping_cart.model.Customer;

public interface ICustomerService {
	
	public void createEmployee(Customer customer);
	
	public List<Customer> findAllCustomer();
	
	public void removeById(Integer customerID);
	
	public Customer updateCustomerById(Integer customerId,Customer customer);
	
	public Customer findById(Integer customerId);
	

}
