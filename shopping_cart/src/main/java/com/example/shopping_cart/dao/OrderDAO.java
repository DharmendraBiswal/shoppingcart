package com.example.shopping_cart.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.shopping_cart.model.Orders;

@Repository
public class OrderDAO {

	@PersistenceContext
	EntityManager entityManager;

	@Transactional
	public Orders save(Orders order) {

		entityManager.persist(order);
		entityManager.refresh(order);
		return entityManager.find(Orders.class, order.getOrderID());

	}

	public Orders getOrderDetail(Integer orderId) {

		return entityManager.find(Orders.class, orderId);
	}

	public List<Orders> getAllOrdersByCustomerId(Integer customerId) {

		return entityManager.createQuery("from Orders where customerID = '" + customerId + "'").getResultList();
	}

	@Transactional
	public Orders cancelOrder(Integer orderId) {
		Orders order = entityManager.find(Orders.class, orderId);

		if (order != null) {
			String status = "Cancelled";
			entityManager.createQuery("update Orders set orderStatus= '" + status + "'where orderID= '" + orderId + "'")
					.executeUpdate();
			entityManager.refresh(order);
			return entityManager.find(Orders.class, orderId);
		}
		return null;
	}

	@Transactional
	public Orders updateOrderStatus(Integer orderId, Orders order) {
		Orders orderById = entityManager.find(Orders.class, orderId);

		if (order != null) {
			entityManager
					.createQuery("update Orders set orderStatus= '" + order.getOrderStatus() + "'where orderID= '" + orderId + "'")
					.executeUpdate();
			entityManager.refresh(orderById);
			return entityManager.find(Orders.class, orderId);
		}
		return null;
	}
	
}
