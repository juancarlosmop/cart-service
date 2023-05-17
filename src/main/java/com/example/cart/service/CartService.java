package com.example.cart.service;



import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.example.cart.dto.RpPayment;
import com.example.cart.dto.RqCart;
import com.example.cart.dto.RqCartItem;
import com.example.cart.dto.RqPayment;
import com.example.cart.model.Cart;
import com.example.cart.model.CartItem;
import com.example.cart.model.Payment;
import com.example.cart.repository.ICartItemRepository;
import com.example.cart.repository.ICartRepository;
import com.example.cart.repository.IPaymentRepository;
import com.example.cart.repository.IProductRepository;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Service
public class CartService implements ICartService{
	/* dependency injection to cartRepository*/
	@Autowired
	private ICartRepository cartRepository;
	
	/* dependency injection to cartItemRepository*/
	@Autowired
	private ICartItemRepository cartItemRepository;
	
	/* dependency injection to paymentRepository*/
	@Autowired
	private IPaymentRepository paymentRepository;
	
	/* dependency injection to productRepository*/
	@Autowired
	private  IProductRepository productRepository;
	
	/*
   	 * (no-javadoc)
   	 * @ see  com.example.cart.service.ICartService#getCarById(int)
   	 * */
	@Override
	public Cart getCarById(int idCar) {
		return cartRepository.findById(idCar);
	}
	
	/*
   	 * (no-javadoc)
   	 * @ see  com.example.cart.service.ICartService#createCar(com.example.cart.dto.RqCart)
   	 * */
	@Override
	public int createCar(RqCart car) {
		
		return cartRepository.save(car);
	}
	
	
	/*
   	 * (no-javadoc)
   	 * @ see  com.example.cart.service.ICartService#getCarById(int)
   	 * */
	@Override
	public double totalCarrito(int idCar) {
		Cart cart=cartRepository.findById(idCar);
		double total=0.0;
		for(CartItem item: cart.getCartItem() ) {
			total+= item.getQuantity()+item.getProduct().getPrice();
		}
		return total;
	}
	
	/*
   	 * (no-javadoc)
   	 * @ see  com.example.cart.service.ICartService#removeItemCart(int)
   	 * */
	public void removeItemCart(int idItemCart) {
		cartItemRepository.deleteItemCartById(idItemCart);
	}
	
	
	/*
   	 * (no-javadoc)
   	 * @ see  com.example.cart.service.ICartService#addItemToCart(com.example.cart.dto.RqCartItem)
   	 * */
	public void addItemToCart(RqCartItem rqCartItem) {
		cartItemRepository.saveItem(rqCartItem);
	}
	
	/*
   	 * (no-javadoc)
   	 * @ see  com.example.cart.service.ICartService#updateItemCartById(com.example.cart.dto.RqCartItem)
   	 * */
	@Override
	public void updateItemCartById(RqCartItem rqCartItem) {
		
		cartItemRepository.updateQuantityItemCartById(rqCartItem);
		
	}

	
	/*
   	 * (no-javadoc)
   	 * @ see  com.example.cart.service.ICartService#savePayment(com.example.cart.dto.RqPayment)
   	 * */
	@Override
	public RpPayment savePayment(RqPayment rqPayment) {
		RpPayment rpPayment = new RpPayment();
		rpPayment.setCode("OK");
		rpPayment.setMessage("The payment was did");
		List<CartItem> items = cartItemRepository.findItemById(rqPayment.getIdCart());
		List<CartItem> itemsReject = this.existItemsReject(items);
		/* if there are not enough stock in a product from the cartitem */
		if(!itemsReject.isEmpty()) {
			rpPayment.setCode("WARNING");
			rpPayment.setItemsReject(itemsReject);
			rpPayment.setMessage("There are some items that they didn't add to shopping cart due to exist");
		}else {
			/* it is updated the stock */
			this.updateStock(items);
			/* it is saving the payment info*/
			paymentRepository.savePayment(rqPayment);
			rpPayment.setCode("OK");
			rpPayment.setMessage("The purchase was did");
		}
		
		return rpPayment;
	}
	
	private void updateStock(List<CartItem> items) {
		log.info("updatin stock");
		for(CartItem item:items) {
			int newStock=item.getProduct().getStock() - item.getQuantity();
			item.getProduct().setStock(newStock);
			 productRepository.updateProductStock(item.getProduct());
		 }
	}
	
	/*
   	 * (no-javadoc)
   	 * @ see  com.example.cart.service.ICartService#existItemsReject(com.example.cart.dto.model.CartItem)
   	 * */
	public List<CartItem> existItemsReject(List<CartItem> items){
		log.info("checkin if there are not enoguh stock");
		List<CartItem> itemsReject = new ArrayList<>();
		for(CartItem item:items) {
			int newStock=item.getProduct().getStock() - item.getQuantity();
			item.getProduct().setStock(newStock);
			if(newStock<= 0) {
				itemsReject.add(item);
			}
			
		}
		return itemsReject;
	}
	
	/*
   	 * (no-javadoc)
   	 * @ see  com.example.cart.service.ICartService#getPaymentBillByEmail(String)
   	 * */
	@Override
	public Payment getPaymentBillByEmail(String email) {
		return paymentRepository.getBillPaymentByEmail(email);
	}
	
	

}
