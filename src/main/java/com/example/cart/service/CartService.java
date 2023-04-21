package com.example.cart.service;



import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cart.model.Cart;
import com.example.cart.model.CartItem;
import com.example.cart.model.Payment;
import com.example.cart.model.RpPayment;
import com.example.cart.model.RqCart;
import com.example.cart.model.RqCartItem;
import com.example.cart.model.RqPayment;
import com.example.cart.repository.ICartItemRepository;
import com.example.cart.repository.ICartRepository;
import com.example.cart.repository.IPaymentRepository;
import com.example.cart.repository.IProductRepository;

@Service
public class CartService implements ICartService{
	
	@Autowired
	private ICartRepository cartRepository;
	
	@Autowired
	private ICartItemRepository cartItemRepository;
	
	@Autowired
	private IPaymentRepository paymentRepository;
	
	@Autowired
	private  IProductRepository productRepository;
	
	@Override
	public Cart getCarById(int idCar) {
		return cartRepository.findById(idCar);
	}

	@Override
	public int createCar(RqCart car) {
		
		return cartRepository.save(car);
	}

	@Override
	public double totalCarrito(int idCar) {
		Cart cart=cartRepository.findById(idCar);
		double total=0.0;
		for(CartItem item: cart.getCartItem() ) {
			total+= item.getQuantity()+item.getProduct().getPrice();
		}
		return total;
	}
	
	public void removeItemCart(int idItemCart) {
		cartItemRepository.deleteItemCartById(idItemCart);
	}
	
	public void addItemToCart(RqCartItem rqCartItem) {
		cartItemRepository.saveItem(rqCartItem);
	}

	@Override
	public void updateItemCartById(RqCartItem rqCartItem) {
		
		cartItemRepository.updateQuantityItemCartById(rqCartItem);
		
	}

	@Override
	public RpPayment savePayment(RqPayment rqPayment) {
		RpPayment rpPayment = new RpPayment();
		rpPayment.setCode("OK");
		rpPayment.setMessage("The payment was did");
		List<CartItem> items = cartItemRepository.findItemById(rqPayment.getIdCart());
		List<CartItem> itemsReject = this.existItemsReject(items);
		if(!itemsReject.isEmpty()) {
			rpPayment.setCode("WARNING");
			rpPayment.setItemsReject(itemsReject);
			rpPayment.setMessage("There are some items that they didn't add to shopping cart due to exist");
		}else {
			for(CartItem item:items) {
				int newStock=item.getProduct().getStock() - item.getQuantity();
				item.getProduct().setStock(newStock);
				 productRepository.updateProductStock(item.getProduct());
			 }
			paymentRepository.savePayment(rqPayment);
			rpPayment.setCode("OK");
			rpPayment.setMessage("The purchase was did");
		}
		
		return rpPayment;
	}
	
	private List<CartItem> existItemsReject(List<CartItem> items){
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
	
	@Override
	public Payment getPaymentBillByEmail(String email) {
		return paymentRepository.getBillPaymentByEmail(email);
	}
	
	

}
