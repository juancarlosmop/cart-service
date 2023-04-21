package com.example.cart.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartItem implements Serializable{
	private int id;
	private Cart cart;
	private Product product;
	private int quantity;
}
