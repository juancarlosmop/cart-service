package com.example.cart.repository;

import java.util.List;

import com.example.cart.dto.RqCart;
import com.example.cart.model.Cart;

/*
 * Interface to manage the operations entity Cart}
 * 
 * @author JC
 * */
public interface ICartRepository {
	/*Method to save a new car
	 * 
	 * @param RqCart car request from cart
	 * @return int
	 * */
	public int save(RqCart car); 
	
	/* Method to find a cart by id
	 * 
	 * @param int id of cart
	 * @return  Cart 
	 * */
	public Cart findById(int id);
	
	/* Method to get AllCarts
	 * 
	 * @return  List<Cart> list of carts
	 * */
	public List<Cart> findAll();
}
