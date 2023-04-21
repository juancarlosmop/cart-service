package com.example.cart.model;

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
	@JsonProperty("id_cart")
	private int idCart;
	@NotBlank
	@JsonProperty("payment_method")
	private String paymentMethod;
	private double amount;
	private int status;
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "dd-MM-yy")
	private Date date;

}
