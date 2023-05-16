package com.example.cart.repository;

import java.util.List;

import com.example.cart.dto.RqUser;
import com.example.cart.model.User;

/*
 * Interface to manage the operations entity User
 * 
 * @author JC
 * */
public interface IUserRepository {
	/*Method to sabe a new user
	 * @Param RqUser user
	 * @return  int
	 * */
	public int save(RqUser user);
	
	/* Method to get a user by id
	 * 
	 * @Param int id
	 * @return  User
	 * */
	public User findUserById(int id);
	
	/* Method to get a user by id
	 * 
	 * @Param String email of user 
	 * @return User user entity
	 * */
	public User findUserByUserEmail(String email);
	
	/* Method to get all Users 
	 *
	 * @return  List<User> list from users
	 * */
	public List<User> findAllUsers();
 
}
