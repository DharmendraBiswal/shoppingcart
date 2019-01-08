package com.example.shopping_cart.dao.test;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.example.shopping_cart.dao.CustomerDAO;
import com.example.shopping_cart.model.Customer;


@RunWith(MockitoJUnitRunner.class)
public class CustomerDaoTest {
	
	@InjectMocks
	private CustomerDAO customerDao;
	
	@Mock
	private EntityManager entityManager;
	@Mock
	Query query;

	@Test
	public void shouldSaveCustomer() {
		
		Customer customer =  new Customer();
		customer.setCustomerID(1);
		customer.setFirstName("Rahul");
		
		Mockito.doNothing().when(entityManager).persist(customer);
		
		customerDao.saveUser(customer);
		Mockito.verify(entityManager).persist(customer);
	
	}
	
	@Test
	public void shouldreturnAllCustomerDetails(){
		
		List<Customer> customers =  new ArrayList<Customer>();
		Customer customer = new Customer();
		customer.setCustomerID(1);
		customer.setFirstName("John");
		customer.setLastName("Hatrage");
		customers.add(customer);
		
		Mockito.when(entityManager.createQuery(Mockito.anyString())).thenReturn(query);
		Mockito.when(entityManager.createQuery(Mockito.anyString()).getResultList()).thenReturn(customers);
		
		Assert.assertNotNull(customerDao.retriveAllCustomer());
		
		Mockito.verify(entityManager, Mockito.times(2)).createQuery(Mockito.anyString());
	}

	@Test
	public void shouldReturnNullForAllCustomerDetails(){
		
		List<Customer> customers =  new ArrayList<Customer>();
		
		Mockito.when(entityManager.createQuery(Mockito.anyString())).thenReturn(query);
		Mockito.when(entityManager.createQuery(Mockito.anyString()).getResultList()).thenReturn(customers);
		
		Assert.assertNull(customerDao.retriveAllCustomer());
		
		Mockito.verify(entityManager, Mockito.times(2)).createQuery(Mockito.anyString());
		
	}
	
	@Test
	public void shouldReturnCustomerDetailsById(){
		Customer customer = new Customer();
		customer.setCustomerID(1);
		
		Mockito.when(entityManager.find(Customer.class, new Integer(1))).thenReturn(customer);
		
		Assert.assertNotNull(customerDao.retriveCustomerById(1));
		
		Mockito.verify(entityManager).find(Customer.class, new Integer(1));
	}
	
	@Test
	public void shouldReturnNullWhenCustomerIdIsNotAvailable(){
		
		Mockito.when(entityManager.find(Customer.class, new Integer(1))).thenReturn(null);
		
		Assert.assertNull(customerDao.retriveCustomerById(1));
		
		Mockito.verify(entityManager).find(Customer.class, new Integer(1));
	}
	
	@Test
	public void shouldremoveCustomer(){
		Customer customer = new Customer();
		customer.setCustomerID(1);
		
		Mockito.when(entityManager.find(Customer.class, new Integer(1))).thenReturn(customer);
		customerDao.removeCustomer(1);
		Mockito.verify(entityManager).find(Customer.class, new Integer(1));
	}
	
	@Test
	public void shouldReturnNullForRetriveCustomer(){
		
		Mockito.when(entityManager.find(Customer.class, new Integer(1))).thenReturn(null);
		customerDao.removeCustomer(1);
		Mockito.verify(entityManager).find(Customer.class, new Integer(1));
		
	}
	
	@Test
	public void shouldUpdateCustomerDetails(){
		
		Customer customer = new Customer();
		customer.setCustomerID(1);
		
		Mockito.when(entityManager.find(Customer.class, new Integer(1))).thenReturn(customer);
		
		Mockito.when(entityManager.createQuery(Mockito.anyString())).thenReturn(query);
		
		Mockito.when(entityManager.createQuery(Mockito.anyString()).executeUpdate()).thenReturn(1);
		
		Mockito.doNothing().when(entityManager).refresh(customer);
		
		Mockito.when(entityManager.find(Customer.class, new Integer(1))).thenReturn(customer);
		
		Assert.assertNotNull(customerDao.updateCustomerDetails(1, customer));
		
		Mockito.verify(entityManager,Mockito.times(2)).createQuery(Mockito.anyString());
		
	}
	

}

