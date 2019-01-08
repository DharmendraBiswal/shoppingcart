package com.example.shopping_cart;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.example.shopping_cart.controller.CustomerController;
import com.example.shopping_cart.implservice.ImplCustomerService;
import com.example.shopping_cart.model.Customer;


@RunWith(MockitoJUnitRunner.class)
public class CustomerControllerTest {
	
	private MockMvc mockMvc;

	@InjectMocks
	CustomerController customerController;
	
	@Mock
	private ImplCustomerService implCustomerService;

	@Test
	public void shouldReturnCustomerDetials() {
		
		
		
		
	}

}
