package com.example.shopping_cart.service.test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.example.shopping_cart.dao.OrderDAO;
import com.example.shopping_cart.implservice.ImplOrdersService;
import com.example.shopping_cart.model.Orders;


@RunWith(MockitoJUnitRunner.class)
public class ImplOrdersServiceTest {
	
	@InjectMocks
	private ImplOrdersService implOrdersService;
	
	@Mock
	private OrderDAO orderDao;

	@Test
	public void shouldSaveOrders() {
		
		List<Orders> productsOrderedSuccessfully = new ArrayList<Orders>();
		
		Orders order = new Orders();
		order.setCustomerID(6);
		order.setOrderID(2);
		order.setOrderDate(new Date(2018-03-19));
		order.setDeliveryDate(new Date(2018-03-27));
		order.setOrderAmount(2875.00);
	
		productsOrderedSuccessfully.add(order);
		Mockito.when(orderDao.save(order)).thenReturn(order);
		
		Assert.assertNotNull(implOrdersService.saveOrder(productsOrderedSuccessfully));
		
		Mockito.verify(orderDao).save(order);
		
	}

	@Test
	public void shouldUpdateOrderStatus(){
		
		Orders order = new Orders();
		order.setCustomerID(6);
		order.setOrderID(18);
		order.setProductID(2);
		order.setOrderDate(new Date(2018-03-19));
		order.setDeliveryDate(new Date(2018-03-27));
		order.setOrderAmount(2875.00);
		
		Mockito.when(orderDao.updateOrderStatus(18, order)).thenReturn(order);
		
		Assert.assertNotNull(implOrdersService.updateOrderStatus(18, order));
		
		Mockito.verify(orderDao).updateOrderStatus(18, order);
		
	}
	
	@Test
	public void shouldCancelOrder(){
		
		Orders order = new Orders();
		order.setOrderID(18);
		
		Mockito.when(orderDao.cancelOrder(18)).thenReturn(order);
		
		Assert.assertNotNull(implOrdersService.cancelOrderByOrderId(18));
		
		Mockito.verify(orderDao).cancelOrder(18);
		
	}
	
	@Test
	public void shouldReturnAllOrdersByCustomerId(){
		
		List<Orders> listOfOrders = new ArrayList<Orders>();
		
		Orders order = new Orders();
		order.setOrderID(18);		
		listOfOrders.add(order);
		
		Mockito.when(orderDao.getAllOrdersByCustomerId(1)).thenReturn(listOfOrders);
		Assert.assertNotNull(implOrdersService.findAllOrdersByCustomerId(1));
		
		Mockito.verify(orderDao).getAllOrdersByCustomerId(1);		
		
		
	}
	
	@Test
	public void shouldReturnOrderDetailsByOrderId(){
		
		Orders order = new Orders();
		order.setCustomerID(6);
		order.setOrderID(18);
		order.setProductID(2);
		order.setOrderDate(new Date(2018-03-19));
		order.setDeliveryDate(new Date(2018-03-27));
		order.setOrderAmount(2875.00);
		
		Mockito.when(orderDao.getOrderDetail(18)).thenReturn(order);
		
		Assert.assertNotNull(implOrdersService.findOrderById(18));
		
		Mockito.verify(orderDao).getOrderDetail(18);
		
	}
}




