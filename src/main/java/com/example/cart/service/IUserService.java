package com.example.cart.service;

import java.util.List;

import com.example.cart.model.RqUser;
import com.example.cart.model.User;

public interface IUserService {
	
	public int createUser(RqUser user) ;
	public User getUserById(int id);
	public User getUserByEmail(String email);
	public List<User> getAllUsers();

}
