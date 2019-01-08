package com.example.shopping_cart.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.shopping_cart.model.Product;
import com.example.shopping_cart.service.IProductService;

@RestController
public class ProductController {

	@Autowired
	private IProductService productServiceImpl;

	@PostMapping(path = "/products/stock", consumes = "application/json", produces = "application/json")
	public List<Product> addToStock(@RequestBody List<Product> products) {

		return productServiceImpl.addProducts(products);

	}

	@PutMapping(path = "/products/update_stock", consumes = "application/json", produces = "application/json")
	public List<Product> updateStock(@RequestBody List<Product> products) {

		return productServiceImpl.updateStock(products);

	}

	@GetMapping(path = "/products", produces = "application/json")
	public List<Product> retriveAllproducts() {

		return productServiceImpl.displayAllProducts();

	}

	@GetMapping(path = "/products/{product_catagory}", produces = "application/json")
	public List<Product> retriveProductsByCatagory(@PathVariable(value = "product_catagory") String productCategory) {

		return productServiceImpl.displayAllProductsByCatagory(productCategory);

	}

	@GetMapping(path = "/products/search/{product_catagory}/{product_type}", produces = "application/json")
	public List<Product> retriveProductsByCatagoryAndType(
			@PathVariable(value = "product_catagory") String productCategory,
			@PathVariable(value = "product_type") String productType) {

		return productServiceImpl.displayProductByCatagoryAndType(productCategory, productType);

	}

	@GetMapping(path = "/products/verify_stock/{product_Id}", produces = "application/json")
	public Integer retriveAvailableStockByProductId(@PathVariable(value = "product_Id") Integer productID) {

		return productServiceImpl.findStockByProductId(productID);

	}

	
}
