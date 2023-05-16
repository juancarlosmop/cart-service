package com.example.cart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cart.dto.RqUser;
import com.example.cart.model.User;
import com.example.cart.repository.IUserRepository;

@Service
public class UserService implements IUserService {
	
	/* dependency injection to userRepository*/
	@Autowired
	private IUserRepository userRepository;
	
	/*
	 * (no-javadoc)
	 * @ see  com.example.cart.service.IUserService#createUser(com.example.cart.dto.RqUser)
	 * */
	@Override
	public int createUser(RqUser user) {
		return userRepository.save(user);
	}
	
	/*
	 * (no-javadoc)
	 * @ see  com.example.cart.service.IUserService#getUserById(int)
	 * */
	@Override
	public User getUserById(int id) {
		return userRepository.findUserById(id);
	}
	
	
	/*
	 * (no-javadoc)
	 * @ see  com.example.cart.service.IUserService#getAllUsers()
	 * */
	@Override
	public List<User> getAllUsers() {
		return userRepository.findAllUsers();
	}
	
	/*
	 * (no-javadoc)
	 * @ see  com.example.cart.service.IUserService#getUserByEmail(String)
	 * */
	@Override
	public User getUserByEmail(String email) {
		return userRepository.findUserByUserEmail(email);
	}

}
