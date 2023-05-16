package com.example.cart.repository;

import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import com.example.cart.dto.RqUser;
import com.example.cart.model.User;

import java.sql.PreparedStatement;

@Repository
public class UserRepository implements IUserRepository{
	/* dependency injection to jdbcTemplate*/
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncode;
	
	/*
	 * (no-javadoc)
	 * @ see  com.example.cart.repository.IUserRepository#save(com.example.cart.dto.RqUser)
	 * */
	@Override
	public int save(RqUser user) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection->{ 
			PreparedStatement ps = connection.prepareStatement(
				" INSERT INTO users(username,email,password) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS
				);
		 	ps.setString(1, user.getUsername());
		 	ps.setString(2, user.getEmail());
		 	ps.setString(3, bCryptPasswordEncode.encode(user.getPassword()));
		  return ps;
		},keyHolder);
		
		return keyHolder.getKey().intValue();
	}
	
	/*
	 * (no-javadoc)
	 * @ see  com.example.cart.repository.IUserRepository#findUserById(int)
	 * */
	@Override
	public User findUserById(int id) {
		return jdbcTemplate.queryForObject("Select * from users where id=?", new BeanPropertyRowMapper<>(User.class),id);
	}
	
	
	/*
	 * (no-javadoc)
	 * @ see  com.example.cart.repository.IUserRepository#findUserByUserEmail(String)
	 * */
	public User findUserByUserEmail(String email) {
		return jdbcTemplate.queryForObject("Select * from users where email=?", new BeanPropertyRowMapper<>(User.class),email);
		
	}
	
	
	/*
	 * (no-javadoc)
	 * @ see  com.example.cart.repository.IUserRepository#findAllUsers()
	 * */
	@Override
	public List<User> findAllUsers() {
		return jdbcTemplate.query("Select * from users",   new BeanPropertyRowMapper<>(User.class));
	}

}
