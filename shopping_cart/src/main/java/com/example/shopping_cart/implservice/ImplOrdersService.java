package com.example.shopping_cart.implservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopping_cart.dao.OrderDAO;
import com.example.shopping_cart.model.Orders;
import com.example.shopping_cart.service.IOrdersService;

@Service(value = "ordersServiceImpl")
public class ImplOrdersService implements IOrdersService {

	@Autowired
	private OrderDAO orderDao;

	@Override
	public Orders findOrderById(Integer orderId) {

		return orderDao.getOrderDetail(orderId);
	}

	@Override
	public List<Orders> findAllOrdersByCustomerId(Integer customerId) {

		return orderDao.getAllOrdersByCustomerId(customerId);
	}

	@Override
	public Orders cancelOrderByOrderId(Integer orderId) {

		return orderDao.cancelOrder(orderId);
	}

	@Override
	public Orders updateOrderStatus(Integer orderId, Orders order) {

		return orderDao.updateOrderStatus(orderId, order);
	}

	@Override
	public List<Orders> saveOrder(List<Orders> orders) {

		List<Orders> productsOrderedSuccessfully = new ArrayList<Orders>();
		for (Orders order : orders) {

			productsOrderedSuccessfully.add(orderDao.save(order));
		}
		return productsOrderedSuccessfully;
	}

}



