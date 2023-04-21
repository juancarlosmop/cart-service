package com.example.cart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cart.model.RqUser;
import com.example.cart.model.User;
import com.example.cart.repository.IUserRepository;

@Service
public class UserService implements IUserService {

	@Autowired
	private IUserRepository userRepository;
	
	@Override
	public int createUser(RqUser user) {
		return userRepository.save(user);
	}

	@Override
	public User getUserById(int id) {
		return userRepository.findUserById(id);
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAllUsers();
	}

	@Override
	public User getUserByEmail(String email) {
		return userRepository.findUserByUserEmail(email);
	}

}
