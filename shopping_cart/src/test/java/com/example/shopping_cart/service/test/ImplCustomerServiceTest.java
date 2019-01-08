package com.example.shopping_cart.service.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.example.shopping_cart.dao.CustomerDAO;
import com.example.shopping_cart.implservice.ImplCustomerService;
import com.example.shopping_cart.model.Customer;

import org.junit.*;

@RunWith(MockitoJUnitRunner.class)
public class ImplCustomerServiceTest {

	@InjectMocks
	private ImplCustomerService implCustomerService;

	@Mock
	CustomerDAO customerDao;

	@Test
	public void shouldCreateEmployee() {

		Customer customer = new Customer();
		customer.setCustomerID(1);

		Mockito.doNothing().when(customerDao).saveUser(customer);
		implCustomerService.createEmployee(customer);
		Mockito.verify(customerDao).saveUser(customer);

	}

	@Test
	public void shouldReturnAllCustomersDetails() {

		List<Customer> customers = new ArrayList<Customer>();
		Customer customer = new Customer();
		customer.setCustomerID(1);
		customers.add(customer);

		Mockito.when(customerDao.retriveAllCustomer()).thenReturn(customers);
		Assert.assertNotNull(implCustomerService.findAllCustomer());

		Mockito.verify(customerDao).retriveAllCustomer();

	}
	
	@Test
	public void shouldRemoveCustomer(){
				
		Mockito.doNothing().when(customerDao).removeCustomer(1);
		implCustomerService.removeById(1);
		
		Mockito.verify(customerDao).removeCustomer(1);
		
	}
	
	
	@Test
	public void shouldUpdateCustomerDetail(){
		
		Customer customer = new Customer();
		customer.setCustomerID(1);
		customer.setUserName("Rohan");
		
		
		
		Mockito.when(customerDao.updateCustomerDetails(1, customer)).thenReturn(customer);
		Assert.assertNotNull(implCustomerService.updateCustomerById(1, customer));
		
		Mockito.verify(customerDao).updateCustomerDetails(1, customer);
		
	}
	
	@Test
	public void shouldFindCustomerById(){
		
		Customer customer = new Customer();
		customer.setCustomerID(1);
		
		Mockito.when(customerDao.retriveCustomerById(1)).thenReturn(customer);
		
		Assert.assertNotNull(implCustomerService.findById(1));
		
		Mockito.verify(customerDao).retriveCustomerById(1);
		
	}

	
	
}
