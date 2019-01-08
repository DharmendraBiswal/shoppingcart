package com.example.shopping_cart.service;

import java.util.List;

import com.example.shopping_cart.model.Orders;

public interface IOrdersService {
	
	public List<Orders> saveOrder(List<Orders> orders);
	
	public Orders findOrderById(Integer orderId);
	
	public List<Orders> findAllOrdersByCustomerId(Integer customerId);
	
	public  Orders cancelOrderByOrderId(Integer orderId);
	
	public Orders updateOrderStatus(Integer orderId,Orders order);

}
