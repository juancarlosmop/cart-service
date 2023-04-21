package com.example.cart.model;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Payment implements Serializable{
	private int id;
	private Cart cart;
	@JsonProperty("payment_method")
	private String paymentMethod;
	private double amount;
	private int status;
	@JsonFormat(shape= JsonFormat.Shape.STRING, pattern = "dd-MM-yy")
	private Date date;
	
}
