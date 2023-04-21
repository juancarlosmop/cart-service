package com.example.cart.repository;

import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.cart.model.RqUser;
import com.example.cart.model.User;

import java.sql.PreparedStatement;

@Repository
public class UserRepository implements IUserRepository{

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public int save(RqUser user) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection->{ 
			PreparedStatement ps = connection.prepareStatement(
				" INSERT INTO users(username,email,password) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS
				);
		 	ps.setString(1, user.getUsername());
		 	ps.setString(2, user.getEmail());
		 	ps.setString(3, user.getPassword());
		  return ps;
		},keyHolder);
		
		return keyHolder.getKey().intValue();
	}

	@Override
	public User findUserById(int id) {
		return jdbcTemplate.queryForObject("Select * from users where id=?", new BeanPropertyRowMapper<>(User.class),id);
	}
	
	public User findUserByUserEmail(String email) {
		return jdbcTemplate.queryForObject("Select * from users where email=?", new BeanPropertyRowMapper<>(User.class),email);
		
	}

	@Override
	public List<User> findAllUsers() {
		return jdbcTemplate.query("Select * from users",   new BeanPropertyRowMapper<>(User.class));
	}

}
