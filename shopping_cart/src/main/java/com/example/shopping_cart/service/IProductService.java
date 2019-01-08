package com.example.shopping_cart.service;

import java.util.List;

import com.example.shopping_cart.model.Product;

public interface IProductService {

	public List<Product> addProducts(List<Product> products);

	public List<Product> updateStock(List<Product> products);
	
	public List<Product> displayAllProducts();
	
	
	public List<Product> displayAllProductsByCatagory(String productCategory);
	
	public List<Product> displayProductByCatagoryAndType(String productCategory,String productType);
	
	
	public Integer findStockByProductId(Integer productId);

}
