package com.example.cart.repository;

import java.util.List;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.cart.dto.RqCart;
import com.example.cart.dto.RqCartItem;
import com.example.cart.model.Cart;
import com.example.cart.model.CartItem;
import com.example.cart.model.User;

@Repository
public class CartRepository implements ICartRepository{

	@Autowired
	private JdbcTemplate jdbctemplate;
	
	@Autowired
	private IUserRepository  userRepository;

	@Autowired
	private ICartItemRepository carITemRepository;
	
	
	/* Method to create a new car
	 * 
	 * @param RqCart request from cart
	 * @return int
	 * */
	@Override
	public int save(RqCart car) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbctemplate.update(connection->{
			PreparedStatement ps = connection.prepareStatement("INSERT INTO carts(id_user) VALUES(?)",Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, car.getIdUser());
			return ps;
		}, keyHolder);
		int idCar= keyHolder.getKey().intValue();
		
		for(RqCartItem item: car.getCartItem()) {
			carITemRepository.saveItem(item);
		}
		
		return idCar;
	}
	
	/* Method to get a cart by id
	 * 
	 * @param int id from cart
	 * @return Cart object 
	 * */
	@Override
	public Cart findById(int id) {
	 return jdbctemplate.queryForObject("SELECT * FROM carts WHERE id=? ",  new CarRowMapper(carITemRepository,userRepository),id);
		
	}
	
	/* Method to get all carts
	 * 
	 * @param int id
	 * @return Cart dto 
	 * */
	@Override
	public List<Cart> findAll() {
		return jdbctemplate.query("SELECT * FROM carts", new CarRowMapper(carITemRepository,userRepository));
	}
	
	/* Class to get each record from database */
	private static final class CarRowMapper implements RowMapper<Cart>{
		 private final ICartItemRepository cartItemRepository;
		 
		 private final IUserRepository userRepository;
		 
		 public CarRowMapper(ICartItemRepository cartItemRepository,IUserRepository userRepository) {
		        this.cartItemRepository = cartItemRepository;
		        this.userRepository = userRepository;
		 }
		 
		/* Method map to iterate each record
		* 
		* @param  ResultSet rs, int rowNum
		* @return Cart 
		* */
		@Override
		public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
			Cart cart= new Cart();
			cart.setId(rs.getInt("id"));
			User user= userRepository.findUserById( rs.getInt("id_user"));
			cart.setUser(user);
			List<CartItem> carItem=cartItemRepository.findItemById( rs.getInt("id") );
			cart.setCartItem(carItem );
			return cart;
		}
		
	}
	

}
