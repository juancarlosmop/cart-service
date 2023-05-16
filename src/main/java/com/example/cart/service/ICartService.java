package com.example.cart.service;


import com.example.cart.dto.RpPayment;
import com.example.cart.dto.RqCart;
import com.example.cart.dto.RqCartItem;
import com.example.cart.dto.RqPayment;
import com.example.cart.model.Cart;
import com.example.cart.model.Payment;

public interface ICartService {
	/* Method to get a Cart by id
	 * 
	 * @param int idCar
	 * @return  Cart entity
	 * */
	public Cart getCarById(int idCar);
	
	/* Method to create a new cart
	 * 
	 * @param  RqCart car reques from Cart
	 * @return  int
	 * */
	public int createCar(RqCart car);
	
	/*
	 * 
	 * @param
	 * @return 
	 * */
	public double totalCarrito(int idCar);
	
	/* Method to remove a ItemCart by idItemCart
	 * 
	 * @param int idItemCart item cart to remove
	 * 
	 * */
	public void removeItemCart(int idItemCart);
	
	/* Method to add a new cartItem
	 * 
	 * @param  RqCartItem rqCartItem request from CartItem
	 * 
	 * */
	public void addItemToCart(RqCartItem rqCartItem);
	
	/* Method to update CartItem throught id
	 * 
	 * @param  RqCartItem rqCartItem reqeust CartItem
	 *  
	 * */
	public void updateItemCartById(RqCartItem rqCartItem);
	
	/* Metod to save a Paymente 
	 * 
	 * @param RqPayment rqPayment request payment
	 * @return  RpPayment responsE from payment
	 * */
	public RpPayment savePayment(RqPayment rqPayment);
	
	/* Method to get a Payment by user email
	 * 
	 * @param user email
	 * @return  Payment
	 * */
	public Payment getPaymentBillByEmail(String email);

}
