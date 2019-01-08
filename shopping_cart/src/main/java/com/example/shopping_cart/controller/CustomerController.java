package com.example.shopping_cart.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopping_cart.model.Customer;
import com.example.shopping_cart.model.Orders;
import com.example.shopping_cart.service.ICustomerService;
import com.example.shopping_cart.service.IOrdersService;
import com.example.shopping_cart.service.IProductService;


@RestController
public class CustomerController {

	@Autowired
	private ICustomerService customerService;

	@Autowired
	private IOrdersService orderServiceImpl;

	@Autowired
	private IProductService productServiceImpl;
	
	@GetMapping(path = "/")
	public void show() {

		System.out.println("Hey Home page");
	}
	
	//@CrossOrigin(origins = "http://localhost:3000")
	@PostMapping(path = "/customer", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseStatus(HttpStatus.CREATED)
	public String createNewCustomer(@RequestBody Customer customer) {
		customerService.createEmployee(customer);
		return "success";

	}
	
	@CrossOrigin(origins = "http://localhost:3000")
	@GetMapping(path = "/customers", produces = {"application/json","application/xml"})
	public List<Customer> findCustomers() {

		return customerService.findAllCustomer();
	}

	@GetMapping(path = "/customers/customer_detail/{id}", produces = {"application/json","application/xml"})
	@ResponseStatus(HttpStatus.OK)
	public Customer findCustomerDetailsById(@PathVariable(value = "id") Integer customerId) {
		return customerService.findById(customerId);

	}

	@DeleteMapping(path = "/customer/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void deleteCustomerById(@PathVariable(value = "id") Integer customerId) {
		customerService.removeById(customerId);
	}

	@PutMapping(path = "/customers/{id}", consumes = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public Customer updateCustomerDetailsById(@PathVariable(value = "id") Integer customerId,
			@RequestBody Customer customer) {
		customerService.updateCustomerById(customerId, customer);
		return customerService.findById(customerId);
	}

	@PostMapping(path = "/customers/orders/products/{customerId}", consumes = "application/json" , produces = "application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public List<Orders> createOrderForCustomer(@PathVariable(value = "customerId") Integer customerID,
			@RequestBody List<Orders> orders) {

		Customer customer = customerService.findById(customerID);
		
		List<Orders> availableProductsForOrder = new ArrayList<Orders>();

		if (customer != null) {
			
			for(Orders order : orders){
				
				if (productServiceImpl.findStockByProductId(order.getProductID()) != 0) {
					
					order.setCustomerID(customerID);
					order.setOrderStatus("Initiated");
					
					availableProductsForOrder.add(order);
				}				
			}

			return orderServiceImpl.saveOrder(availableProductsForOrder);
		}
		return null;

	}

	@PutMapping(path = "/customers/orders/cancel_order/{customer_id}/{order_id}", produces = "application/json")
	public Orders cancelOrderByCustomer(@PathVariable(value = "customer_id") Integer customerID,
			@PathVariable(value = "order_id") Integer orderId) {

		Customer customer = customerService.findById(customerID);
		if (customer != null && orderServiceImpl.findOrderById(orderId) != null) {

			if (orderServiceImpl.findOrderById(orderId).getOrderStatus().equals("Initiated")) {
				return orderServiceImpl.cancelOrderByOrderId(orderId);
			}

		}

		return null;

	}

}

