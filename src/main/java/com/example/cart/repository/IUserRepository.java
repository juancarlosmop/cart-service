package com.example.cart.repository;

import java.util.List;

import com.example.cart.model.RqUser;
import com.example.cart.model.User;

public interface IUserRepository {
	
	public int save(RqUser user);
	public User findUserById(int id);
	public User findUserByUserEmail(String email);
	public List<User> findAllUsers();
 
}
