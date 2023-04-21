package com.example.cart.service;


import com.example.cart.model.Cart;
import com.example.cart.model.Payment;
import com.example.cart.model.RpPayment;
import com.example.cart.model.RqCart;
import com.example.cart.model.RqCartItem;
import com.example.cart.model.RqPayment;

public interface ICartService {
	
	public Cart getCarById(int idCar);
	public int createCar(RqCart car);
	public double totalCarrito(int idCar);
	public void removeItemCart(int idItemCart);
	public void addItemToCart(RqCartItem rqCartItem);
	public void updateItemCartById(RqCartItem rqCartItem);
	public RpPayment savePayment(RqPayment rqPayment);
	public Payment getPaymentBillByEmail(String email);

}
