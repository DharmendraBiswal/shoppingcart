package com.example.shopping_cart.service.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import com.example.shopping_cart.dao.ProductDAO;
import com.example.shopping_cart.implservice.ImplProductService;
import com.example.shopping_cart.model.Product;


@RunWith(MockitoJUnitRunner.class)
public class ImplProductServiceTest {
	
	@InjectMocks
	private ImplProductService implProductService;
	
	@Mock
	private ProductDAO productDAO;

	@Test
	public void shouldAddProductToStock() {
		
		List<Product> products =  new ArrayList<Product>();
		
		Product product = new Product();
		product.setProductID(1);
		product.setProductCategory("Men");
		product.setProductType("Shirt");
		product.setProductBrand("Lee");
		product.setProductPrice(1751.89);
		product.setStockAvailable(50);
		
		products.add(product);
		
		Mockito.when(productDAO.saveProducts(products)).thenReturn(products);
		
		Assert.assertNotNull(implProductService.addProducts(products));
		
		Mockito.verify(productDAO).saveProducts(products);
	}
	
	@Test
	public void shouldUpdateStockForExistingProduct(){
		
		List<Product> updatedproducts =  new ArrayList<Product>();
		
		Product product = new Product();
		product.setProductID(1);
		product.setStockAvailable(100);
		
		updatedproducts.add(product);
		
		Mockito.when(productDAO.updateStocks(updatedproducts)).thenReturn(updatedproducts);
		
		Assert.assertNotNull(implProductService.updateStock(updatedproducts));
		
		Mockito.verify(productDAO).updateStocks(updatedproducts);
		
		
	}
	
	@Test
	public void shouldShowAllProducts(){
		
		List<Product> availableProducts =  new ArrayList<Product>();
		Product product = new Product();
		product.setProductID(1);

		availableProducts.add(product);
		
		Mockito.when(productDAO.showAllProducts()).thenReturn(availableProducts);
		
		Assert.assertNotNull(implProductService.displayAllProducts());
		
		Mockito.verify(productDAO).showAllProducts();
		
		
	}
	
	@Test
	public void shouldShowAllProductsByCategory(){
		
		List<Product> availableProducts =  new ArrayList<Product>();
		Product product = new Product();
		product.setProductID(1);
		product.setProductCategory("Men");
		availableProducts.add(product);
		
		Mockito.when(productDAO.showAllProductsByCatagory("Men")).thenReturn(availableProducts);
		
		Assert.assertNotNull(implProductService.displayAllProductsByCatagory("Men"));
		
		Mockito.verify(productDAO).showAllProductsByCatagory("Men");
		
	}

	@Test
	public void shouldShowAllProductByCategoryAndType(){
		
		List<Product> availableProductsByCategoryAndType =  new ArrayList<Product>();
		Product product = new Product();
		product.setProductID(1);
		product.setProductCategory("Men");
		product.setProductType("Shirt");
		availableProductsByCategoryAndType.add(product);
		
		Mockito.when(productDAO.showProductByCatagoryAndType("Men", "Shirt")).thenReturn(availableProductsByCategoryAndType);
		
		Assert.assertNotNull(implProductService.displayProductByCatagoryAndType("Men", "Shirt"));
		
		Mockito.verify(productDAO).showProductByCatagoryAndType("Men", "Shirt");
		
	}
	
	
	@Test
	public void shouldReturnStockByProductId(){
		
		Product product = new Product();
		product.setProductID(1);
		product.setStockAvailable(50);
		
		Mockito.when(productDAO.retriveStockById(1)).thenReturn(50);
		
		Assert.assertEquals(new Integer(50), implProductService.findStockByProductId(1));
		
		Mockito.verify(productDAO).retriveStockById(1);
		
	}
	
	
	
}

