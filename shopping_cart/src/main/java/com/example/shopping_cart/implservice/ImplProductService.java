package com.example.shopping_cart.implservice;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.shopping_cart.dao.ProductDAO;
import com.example.shopping_cart.model.Product;
import com.example.shopping_cart.service.IProductService;

@Service(value = "productServiceImpl")
public class ImplProductService implements IProductService {

	@Autowired
	private ProductDAO productDao;

	@Override
	public List<Product> addProducts(List<Product> products) {

		return productDao.saveProducts(products);
	}

	@Override
	public List<Product> updateStock(List<Product> products) {

		return productDao.updateStocks(products);
	}

	@Override
	public List<Product> displayAllProducts() {
		
		return productDao.showAllProducts();
	}

	@Override
	public List<Product> displayAllProductsByCatagory(String productCategory) {
		
		return productDao.showAllProductsByCatagory(productCategory);
	}

	@Override
	public List<Product> displayProductByCatagoryAndType(String productCategory, String productType) {
		
		return productDao.showProductByCatagoryAndType(productCategory, productType);
	}

	@Override
	public Integer findStockByProductId(Integer productId) {
		
		return productDao.retriveStockById(productId);
	}

}



