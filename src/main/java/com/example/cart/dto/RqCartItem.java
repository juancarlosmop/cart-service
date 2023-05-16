package com.example.cart.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RqCartItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	@JsonProperty("id_product")
	private int idProduct;
	@JsonProperty("id_cart")
	private int idCart;
	private int quantity;
}
