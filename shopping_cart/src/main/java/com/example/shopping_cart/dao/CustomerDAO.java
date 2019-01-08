package com.example.shopping_cart.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.example.shopping_cart.model.Customer;

@Repository
public class CustomerDAO {

	@PersistenceContext
	EntityManager entityManager;

	@Transactional
	public void saveUser(Customer customer) {
		entityManager.persist(customer);

	}

	public List<Customer> retriveAllCustomer() {

		List<Customer> customers = entityManager.createQuery("from Customer").getResultList();

		return customers.size() > 0 ? customers : null;
	}

	public Customer retriveCustomerById(Integer id) {
		Customer customer = entityManager.find(Customer.class, id);
		if (customer != null) {
			return customer;
		}
		return null;
	}

	@Transactional
	public void removeCustomer(Integer customerID) {

		Customer customer = entityManager.find(Customer.class, customerID);
		if (customer != null) {
			entityManager.remove(customer);
		}

	}

	@Transactional
	public Customer updateCustomerDetails(Integer customerId, Customer customer) {

		Customer customerById = entityManager.find(Customer.class, customerId);

		if (customerById != null) {

			entityManager.createQuery("update Customer set userName= '" + customer.getUserName()
					+ "' where customerID='" + customerId + "'").executeUpdate();

			entityManager.refresh(customerById);
			//return retriveCustomerById(customerId);
			return entityManager.find(Customer.class, customerId);

		}
		return null;
	}
}



