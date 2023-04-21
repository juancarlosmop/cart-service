package com.example.cart.model;

import java.io.Serializable;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RqProduct implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2486536657961675002L;

	@NotNull
	private String name;

	@Positive
	private double price;
	
	@NotNull
	private String description;

}
