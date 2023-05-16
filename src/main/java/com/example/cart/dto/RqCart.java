package com.example.cart.dto;

import java.io.Serializable;
import java.util.List;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RqCart implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("id_user")
	@NotNull
	private int idUser;
	@NotNull
	@JsonProperty("cart_items")
	private List<RqCartItem> cartItem;
}
