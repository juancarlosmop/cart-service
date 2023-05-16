package com.example.cart.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User implements Serializable {
	 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	 private String username;
	 private String email;
	 private String password;
}
