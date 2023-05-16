package com.example.cart.dto;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RqPayment implements Serializable  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@JsonProperty("id_cart")
	private int idCart;
	@NotBlank
	@JsonProperty("payment_method")
	private String paymentMethod;
	private double amount;
	private int status;
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "dd-MM-yy")
	@JsonProperty("date_in")
	private Date date_in;

}
