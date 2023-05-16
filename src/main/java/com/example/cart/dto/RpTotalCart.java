package com.example.cart.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RpTotalCart extends RpBase implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double total;
}
