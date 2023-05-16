package com.example.cart.repository;

import java.util.List;

import com.example.cart.dto.RqCartItem;
import com.example.cart.model.CartItem;

/*
 * Interface to manage the operations entity CartItem
 * 
 * @author JC
 * */
public interface ICartItemRepository {
	
	/*Method to save a new carItem
	 * 
	 *@param  RqCartItem car response from  CartItem
	 * */
	public void saveItem(RqCartItem car);
	
	/* Method to find a cartItemById
	 * 
	 * @param int id, id from CartItem
	 * @return  List<CartItem> list from CartItem
	 * */
	public List<CartItem> findItemById(int id);
	
	/* Method to find a cartItemById
	 * 
	 * @param int id from cartItem
	 *  
	 * */
	public void deleteItemCartById(int id);
	
	/* Method to find a cartItemById
	 * 
	 * @param RqCartItem rqCartItem request from CartItem
	 * 
	 * */
	public void updateQuantityItemCartById(RqCartItem rqCartItem);

}
