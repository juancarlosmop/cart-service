package com.example.cart.dto;

import java.io.Serializable;
import java.util.List;

import com.example.cart.model.CartItem;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RpPayment implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("code")
	private String code;
	@JsonProperty("message")
	private String message;
	@JsonProperty("items_reject")
	List<CartItem> itemsReject;

}
