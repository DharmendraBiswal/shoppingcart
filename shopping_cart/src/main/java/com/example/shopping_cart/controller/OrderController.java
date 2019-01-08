package com.example.shopping_cart.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopping_cart.model.Orders;
import com.example.shopping_cart.service.IOrdersService;

@RestController
public class OrderController {

	@Autowired
	private IOrdersService ordersServiceImpl;

	@PostMapping(path = "/customers/orders/order/{customer_id}", consumes = "application/json", produces = "application/json")
	public List<Orders> createOrder(@PathVariable(value = "customer_id") Integer customerId, @RequestBody List<Orders> orders) {

		List<Orders> initialteOrders = new ArrayList<Orders>();
		for(Orders order : orders){
			order.setCustomerID(customerId);
			order.setOrderStatus("Initiated");
			initialteOrders.add(order);
		}
		
		return ordersServiceImpl.saveOrder(initialteOrders);

	}

	@GetMapping(path = "/customers/orders/order_detail/{order_id}", produces = "application/json")
	public Orders retriveOrder(@PathVariable(value = "order_id") Integer orderId) {

		return ordersServiceImpl.findOrderById(orderId);

	}

	@GetMapping(path = "/customers/orders/customer_orders/{customer_id}", produces = "application/json")
	public List<Orders> retriveAllOrders(@PathVariable(value = "customer_id") Integer customerId) {

		return ordersServiceImpl.findAllOrdersByCustomerId(customerId);

	}

	@PutMapping(path = "/customers/orders/cancel_order/{order-id}", produces = "application/json")
	public Orders cancelOrder(@PathVariable(value = "order-id") Integer orderId) {

		return ordersServiceImpl.cancelOrderByOrderId(orderId);

	}

	@PutMapping(path = "/orders/change_order_status/{order-id}", consumes = "application/json" ,produces = "application/json")
	public Orders changeOrderStatus(@PathVariable(value = "order-id") Integer orderId,
			@RequestBody Orders order) {

		return ordersServiceImpl.updateOrderStatus(orderId, order);

	}

}
