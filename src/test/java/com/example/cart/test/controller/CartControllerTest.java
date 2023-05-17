package com.example.cart.test.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import java.util.ArrayList;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.cart.dto.RqCart;
import com.example.cart.dto.RqCartItem;
import com.example.cart.dto.RqPayment;
import com.example.cart.dto.RpPayment;
import com.example.cart.model.Cart;
import com.example.cart.model.CartItem;
import com.example.cart.model.Product;
import com.example.cart.model.Payment;
import com.example.cart.model.User;
import com.example.cart.repository.ICartItemRepository;
import com.example.cart.repository.IPaymentRepository;
import com.example.cart.repository.IProductRepository;
import com.example.cart.service.CartService;

@SpringBootTest
@AutoConfigureMockMvc
public class CartControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private CartService cartService;
	
	@Mock
	private ICartItemRepository cartItemRepository;
	
	
	@Mock
	private IPaymentRepository paymentRepository;
	
	@Mock
	private IProductRepository productRepository;

	@Test
	void getCartTest() throws Exception {
		//give
		Cart cart = new Cart();
		cart.setId(1);
		// add user
		User user = new User();
		user.setId(1);
		user.setUsername("Juan Carlos");
		user.setEmail("jcarlos@gmail.com");
		cart.setUser(user);

		// add list CartItem
		Product product = new Product();
		product.setId(1);
		product.setName("coca");
		CartItem cartItem = new CartItem();
		cartItem.setId(1);
		cartItem.setProduct(product);
		cartItem.setQuantity(3);
		cartItem.setProduct(product);
		List<CartItem> ls = new ArrayList<CartItem>();
		ls.add(cartItem);
		cart.setCartItem(ls);
		// when
		when(cartService.getCarById(1)).thenReturn(cart);
		
		// then
		mockMvc.perform(MockMvcRequestBuilders.get("/cart/get-cart/{id}", 1)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.is(1)))
				.andExpect(MockMvcResultMatchers.jsonPath("$.user.username", Matchers.is("Juan Carlos")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.cartItem[0].product.name", Matchers.is("coca")))
				.andExpect(MockMvcResultMatchers.jsonPath("$.cartItem[0].id", Matchers.is(1)));
		// assert
		verify(cartService, times(1)).getCarById(1);
	}

	@Test
	void createCarTest() throws Exception {
		// give
		RqCartItem rqCartItem = new RqCartItem();
		rqCartItem.setIdProduct(1);
		rqCartItem.setIdCart(1);
		rqCartItem.setQuantity(2);
		List<RqCartItem> listCartItem = new ArrayList<>();
		listCartItem.add(rqCartItem);

	
		RqCart rqCart = new RqCart();
		rqCart.setIdUser(1);
		rqCart.setCartItem(listCartItem);
		
		// when
		cartService.createCar(rqCart);
		
		//then
		mockMvc.perform(MockMvcRequestBuilders.post("/cart/create-car/").contentType(MediaType.APPLICATION_JSON)
				.content(this.convertOjectToJson(rqCart))).andExpect(MockMvcResultMatchers.status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.code").value("OK"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("The cart was created"));

		verify(cartService).createCar(rqCart);
	}

	@Test
	void getTotalTest() throws Exception {
		//give
		double expectedTotal = 10.0;
		when(cartService.totalCarrito(1)).thenReturn(expectedTotal);
		//then
		mockMvc.perform(MockMvcRequestBuilders.get("/cart/get-total/{id}",1))
		        .andExpect(status().isOk())
		        .andExpect(MockMvcResultMatchers.jsonPath("$.code").value("OK"))
		        .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("The total prices from this client"))
		        .andExpect(MockMvcResultMatchers.jsonPath("$.total").value(10.0));
	
		
		verify(cartService,times(1)).totalCarrito(1);
	}

	@Test
	void removeItemTest() throws Exception {
		//give 
		int idItemCart = 1;
		
		//then
		mockMvc.perform(MockMvcRequestBuilders.get("/cart/remove-item/{id}", 1)).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.code").value("OK"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("The element was remove"));

		verify(cartService, times(1)).removeItemCart(idItemCart);
	}

	@Test
	void addItemTest() throws Exception {
		//give
		RqCartItem rqCartItem = new RqCartItem();
		rqCartItem.setIdCart(1);
		rqCartItem.setIdProduct(1);
		rqCartItem.setQuantity(1);
		//when
		cartService.addItemToCart(rqCartItem);
		//then
		mockMvc.perform(MockMvcRequestBuilders.post("/cart/add-element-cart").contentType(MediaType.APPLICATION_JSON)
				.content(this.convertOjectToJson(rqCartItem))).andExpect(status().isCreated())
				.andExpect(jsonPath("$.code").value("OK"))
				.andExpect(jsonPath("$.message").value("there is a new item add"));

		verify(cartService, times(1)).addItemToCart(rqCartItem);
	}

	@Test
	void updateCarItemTest() throws Exception {
		// give
		RqCartItem rqCartItem = new RqCartItem();
		rqCartItem.setId(1);
		rqCartItem.setIdProduct(1);
		rqCartItem.setIdCart(1);
		rqCartItem.setQuantity(3);
		List<RqCartItem> listCartItem = new ArrayList<>();
		listCartItem.add(rqCartItem);
		
		RqCart rqCart = new RqCart();
		rqCart.setIdUser(1);
		rqCart.setCartItem(listCartItem);
		
		//when
	    cartService.createCar(rqCart);
		
		//then
		mockMvc.perform(MockMvcRequestBuilders.post("/cart/update-element-cart/")
				.contentType(MediaType.APPLICATION_JSON).content(this.convertOjectToJson(rqCart)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.code").value("OK"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("The items were updating"));

		verify(cartService, times(1)).createCar(rqCart);
	}
	
	

	@Test
	void makePayment() throws Exception {
		//give
		RqPayment rqPayment = new RqPayment();
		rqPayment.setIdCart(1);
		rqPayment.setAmount(20.00);
		rqPayment.setPaymentMethod("visa");
		
		
		RpPayment expectedPayment = new RpPayment();
	    expectedPayment.setCode("OK");
	    expectedPayment.setMessage("The purchase was did");
	    
	    //when
	    when(cartService.savePayment(rqPayment)).thenReturn(expectedPayment);
	    
	    //then
		mockMvc.perform(MockMvcRequestBuilders.post("/cart/make-payment").contentType(MediaType.APPLICATION_JSON)
				.content(this.convertOjectToJson(rqPayment))).andExpect(status().isCreated())
				.andExpect(MockMvcResultMatchers.jsonPath("$.code").value("OK")).andExpect(MockMvcResultMatchers.jsonPath("$.message").value("The purchase was did"));

		verify(cartService, times(1)).savePayment(rqPayment);
	
	}


	@Test
	void getBillTest() throws Exception {
		//give
		Payment payment = new Payment();
		payment.setId(1);
		payment.setPaymentMethod("visa");
		payment.setStatus(1);
		payment.setAmount(12.00);
		// when
		when(cartService.getPaymentBillByEmail("juan@gmail.com")).thenReturn(payment);

		// then
		mockMvc.perform(MockMvcRequestBuilders.get("/cart/get-bill/{email}", "juan@gmail.com"))
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.payment_method").value("visa"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.status").value(1))
				.andExpect(MockMvcResultMatchers.jsonPath("$.amount").value(12.00));

		verify(cartService).getPaymentBillByEmail("juan@gmail.com");

	}

	public String convertOjectToJson(Object object) {

		ObjectMapper objectMapper = new ObjectMapper();
		String json = "";
		try {
			json = objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return json;
	}

}
