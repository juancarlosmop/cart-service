package com.example.cart.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RpBase implements Serializable{
	private String code;
	private String message;
}
