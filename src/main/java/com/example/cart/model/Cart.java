package com.example.cart.model;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Cart implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4295610273169738217L;
	private int id;
	private User user;
	private List<CartItem> cartItem;
}
