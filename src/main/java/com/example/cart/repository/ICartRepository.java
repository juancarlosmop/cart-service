package com.example.cart.repository;

import java.util.List;

import com.example.cart.model.Cart;
import com.example.cart.model.RqCart;

public interface ICartRepository {
	
	public int save(RqCart car); 
	public Cart findById(int id);
	public List<Cart> findAll();
}
