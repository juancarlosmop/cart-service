package com.example.cart.service;

import java.util.List;

import com.example.cart.dto.RqUser;
import com.example.cart.model.User;

public interface IUserService {
	/* Method to create a new user
	 * 
	 * @param RqUser user request from user
	 * @return int when the record was storage 
	 * */
	public int createUser(RqUser user) ;
	
	/* Method to get a user by id
	 * 
	 * @param int id of user
	 * @return  User entity user
	 * */
	public User getUserById(int id);
	
	/* Method to get a user by email
	 * 
	 * @param email from user
	 * @return User entity
	 * */
	public User getUserByEmail(String email);
	
	/* Method to get a List users
	 * 
	 * @return  List<User> list of users
	 * */
	public List<User> getAllUsers();

}
