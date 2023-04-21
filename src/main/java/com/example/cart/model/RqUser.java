package com.example.cart.model;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RqUser implements Serializable {
	@NotNull 
	private String username;
	@Email
	private String email;
	@NotNull
	private String password;
}
