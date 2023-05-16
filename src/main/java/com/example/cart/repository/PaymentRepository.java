package com.example.cart.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.cart.dto.RqPayment;
import com.example.cart.model.Cart;
import com.example.cart.model.Payment;
import com.example.cart.model.User;

@Repository
public class PaymentRepository implements IPaymentRepository{
	
	/* dependency injection to jdbcTemplate*/
	@Autowired 
	private JdbcTemplate jdbcTemplate;
	
	/* dependency injection to userRepository*/
	@Autowired
	private IUserRepository userRepository;
	
	private static final String joinqueryPayment ="SELECT pay.payment_method,pay.amount,pay.status,pay.date_in,pay.id_cart,us.id as user_id FROM payments as pay JOIN carts as cart on pay.id_cart=cart.id JOIN users as us on cart.id_user = us.id WHERE us.email =?";
	/*
	 * (no-javadoc)
	 * @ see  com.example.cart.repository.IPaymentRepository#savePayment(com.example.cart.dto.RqPayment)
	 * */
	@Override
	public int savePayment(RqPayment payment) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection->{
			PreparedStatement ps = connection.prepareStatement("INSERT INTO payments(id_cart,payment_method,amount,status,date_in) VALUES(?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, payment.getIdCart());
			ps.setString(2, payment.getPaymentMethod());
			ps.setDouble(3, payment.getAmount());
			ps.setInt(4, payment.getStatus());
			ps.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			return ps;
		}, keyHolder);
		return keyHolder.getKey().intValue();
	}
	
	/*
	 * (no-javadoc)
	 * @ see  com.example.cart.repository.IPaymentRepository#getBillPaymentByEmail(String email)
	 * */
	@Override
	public Payment getBillPaymentByEmail(String email ) {
		 return jdbcTemplate.queryForObject(joinqueryPayment,  new PaymentRowMapper(userRepository),email);
		
	}
	/* Class to get each record from database */
	private static final class PaymentRowMapper implements RowMapper<Payment>{
		private final IUserRepository userRepository;
		
		public PaymentRowMapper(IUserRepository userRepository) {
			this.userRepository =userRepository;
		}
		/* Method to get a Payment*/
		@Override
		public Payment mapRow(ResultSet rs, int rowNum) throws SQLException {
			Payment payment = new Payment();
			payment.setPaymentMethod(rs.getString("payment_method"));
			payment.setStatus( rs.getInt("status"));
			payment.setAmount( rs.getDouble("amount"));
			payment.setDateIn( rs.getDate("fecha"));
			Cart cart = new Cart();
			User user = userRepository.findUserById( rs.getInt("user_id"));
			cart.setUser(user);
			payment.setCart(cart);
			
			return payment;
		}
		
	}
}
