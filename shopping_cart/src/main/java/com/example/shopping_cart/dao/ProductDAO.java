package com.example.shopping_cart.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.example.shopping_cart.model.Product;

@Repository
public class ProductDAO {

	@PersistenceContext
	EntityManager entityManager;

	@Transactional
	public List<Product> saveProducts(List<Product> products) {

		List<Product> newProductsAdded = new ArrayList<Product>();

		if (products.size() != 0) {
			for (Product product : products) {

				entityManager.persist(product);
				entityManager.refresh(product);
				newProductsAdded.add(entityManager.find(Product.class, product.getProductID()));

			}

			return newProductsAdded;
		}

		return null;

	}

	@Transactional
	public List<Product> updateStocks(List<Product> products) {

		List<Product> stockUpdatedProducts = new ArrayList<Product>();

		for (Product product : products) {

			Product existingProduct = entityManager.find(Product.class, product.getProductID());

			if (existingProduct != null) {

				entityManager.createQuery("update Product set stockAvailable= '"
						+ (existingProduct.getStockAvailable() + product.getStockAvailable()) + "'where productID= '"
						+ existingProduct.getProductID() + "'").executeUpdate();
				entityManager.refresh(existingProduct);
				stockUpdatedProducts.add(entityManager.find(Product.class, existingProduct.getProductID()));
			}

		}
		return stockUpdatedProducts;

	}

	public List<Product> showAllProducts() {

		return entityManager.createQuery("from Product").getResultList();
	}

	public List<Product> showAllProductsByCatagory(String productCategory) {

		return entityManager.createQuery("from Product where productCategory= '" + productCategory + "'")
				.getResultList();

	}

	public List<Product> showProductByCatagoryAndType(String productCategory, String productType) {

		return entityManager.createQuery(
				"from Product where productCategory= '" + productCategory + "' and productType= '" + productType + "'")
				.getResultList();

	}

	public Integer retriveStockById(Integer productId) {

		Product existingProduct = entityManager.find(Product.class, productId);

		if (existingProduct != null) {
			return existingProduct.getStockAvailable();
		}

		return null;

	}

}
