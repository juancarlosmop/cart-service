package com.example.cart.repository;

import java.util.List;

import com.example.cart.model.CartItem;
import com.example.cart.model.RqCartItem;

public interface ICartItemRepository {
	public void saveItem(RqCartItem car);
	public List<CartItem> findItemById(int id);
	public void deleteItemCartById(int id);
	public void updateQuantityItemCartById(RqCartItem rqCartItem);

}
