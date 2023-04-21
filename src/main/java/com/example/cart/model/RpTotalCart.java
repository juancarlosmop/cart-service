package com.example.cart.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RpTotalCart extends RpBase implements Serializable{
	private double total;
}
