package com.example.shopping_cart.implservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopping_cart.dao.CustomerDAO;
import com.example.shopping_cart.model.Customer;
import com.example.shopping_cart.service.ICustomerService;

@Service(value = "showServiceImpl")
public class ImplCustomerService implements ICustomerService {

	@Autowired
	private CustomerDAO customerDao;

	@Override
	public void createEmployee(Customer customer) {
		customerDao.saveUser(customer);

	}

	@Override
	public List<Customer> findAllCustomer() {

		List<Customer> customers = customerDao.retriveAllCustomer();

		return customers;
	}

	@Override
	public void removeById(Integer customerID) {

		customerDao.removeCustomer(customerID);

	}

	@Override
	public Customer updateCustomerById(Integer customerId, Customer customer) {

		return customerDao.updateCustomerDetails(customerId, customer);
	}

	@Override
	public Customer findById(Integer customerId) {

		return customerDao.retriveCustomerById(customerId);
	}

}

