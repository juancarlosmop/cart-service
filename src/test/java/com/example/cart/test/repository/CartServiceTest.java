package com.example.cart.test.repository;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.ArrayList;

import com.example.cart.dto.RpPayment;
import com.example.cart.dto.RqCart;
import com.example.cart.dto.RqCartItem;
import com.example.cart.dto.RqPayment;
import com.example.cart.model.Cart;
import com.example.cart.model.Payment;
import com.example.cart.repository.ICartItemRepository;
import com.example.cart.repository.ICartRepository;
import com.example.cart.repository.IPaymentRepository;
import com.example.cart.repository.IProductRepository;
import com.example.cart.service.CartService;
@SpringBootTest
public class CartServiceTest {
	
	@Mock
	private ICartRepository cartRepository;
	
	@InjectMocks
	private CartService  cartService;
	
	
	
	@Mock
	private ICartItemRepository cartItemRepository;
	
	
	@Mock
	private IPaymentRepository paymentRepository;
	
	@Mock
	private IProductRepository productRepository;
	
	@Test
	void createCartTest() {
		//give
		RqCartItem  rqCartItem = new RqCartItem();
		rqCartItem.setIdCart(1);
		rqCartItem.setIdProduct(1);
		rqCartItem.setQuantity(2);
		
		List<RqCartItem> list = new ArrayList<>();
		list.add(rqCartItem);
		RqCart rqCart = new RqCart();
		rqCart.setIdUser(1);
		when(cartRepository.save(rqCart)).thenReturn(1);
		//when
		cartService.createCar(rqCart);
		
		//then
		verify(cartRepository,times(1)).save(rqCart);
		
	}
	
	@Test
	void getCartByIdTest(){
		//give
		Cart cart = new Cart();
		cart.setId(1);
		when(cartRepository.findById(1)).thenReturn(cart);
		//when
		Cart findCart=cartService.getCarById(1);
		//assert
		assertThat(findCart).isNotNull();
		assertEquals(cart,findCart);
		verify(cartRepository).findById(1);
	}
	
	@Test
	void removeItemCartTest() {
		//give
		int idItemCart = 1;
		//when
		cartService.removeItemCart(idItemCart);
		//then
		verify(cartItemRepository).deleteItemCartById(idItemCart);
	}
	
	
	@Test
	void addItemCartTest() {
		//give
		RqCartItem rqCartItem = new RqCartItem();
		rqCartItem.setIdCart(1);
		rqCartItem.setIdProduct(1);
		rqCartItem.setQuantity(1);
		//when
		cartService.addItemToCart(rqCartItem);
		//then
		verify(cartItemRepository).saveItem(rqCartItem);
	}
	

	@Test
	void updateCartItemTest() {
		//give
		RqCartItem rqCartItem = new RqCartItem();
		rqCartItem.setId(1);
		rqCartItem.setIdCart(1);
		rqCartItem.setIdProduct(1);
		rqCartItem.setQuantity(1);
		//when
		cartService.updateItemCartById(rqCartItem);
		//then
		verify(cartItemRepository).updateQuantityItemCartById(rqCartItem);
	}
	
	@Test
	void getPayments() {
		//give
		Payment  payment = new Payment();
		payment.setId(1);
		payment.setAmount(20.00);
		payment.setPaymentMethod("visa");
		when(paymentRepository.getBillPaymentByEmail("juan@gmail.com")).thenReturn(payment);
		//when
		Payment findPayment=cartService.getPaymentBillByEmail("juan@gmail.com");
		//then
		assertThat(findPayment).isNotNull();
		assertEquals(payment,findPayment);
		verify(paymentRepository,times(1)).getBillPaymentByEmail("juan@gmail.com");
		
	}
	
	@Test
	void makePaid() {
		//give
		RqPayment  rqPayment = new RqPayment();
		rqPayment.setIdCart(1);
		rqPayment.setAmount(20.00);
		rqPayment.setPaymentMethod("visa");
		
		//when
		RpPayment returnPayment=cartService.savePayment(rqPayment);
		
		//then
		assertThat(returnPayment).isNotNull();
		verify(paymentRepository,times(1)).savePayment(rqPayment);
	}
	
	
}
