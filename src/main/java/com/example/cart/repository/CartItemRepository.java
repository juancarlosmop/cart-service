package com.example.cart.repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.example.cart.dto.RqCartItem;
import com.example.cart.model.Cart;
import com.example.cart.model.CartItem;
import com.example.cart.model.Product;

@Repository
public class CartItemRepository implements ICartItemRepository {
	/*dependency injection to jdbcTemplate*/
	@Autowired
	private JdbcTemplate jdbcTemplate;
	/* dependency injection to productRepository */
	@Autowired
	private IProductRepository productRepository;
	
	
	/* Method to save an item
	* 
	* @param  RqCartItem request fromo CartItem
	*
	* */
	@Override
	public void saveItem(RqCartItem cartItem) {
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbcTemplate.update(connection->{
			PreparedStatement ps = connection.prepareStatement("INSERT INTO cart_items(id_cart,id_product,quantity) VALUES(?,?,?)", Statement.RETURN_GENERATED_KEYS);
			ps.setInt(1, cartItem.getIdCart());
			ps.setInt(2, cartItem.getIdProduct());
			ps.setDouble(3, cartItem.getQuantity());
			return ps;
		}, keyHolder);
		
	}

	/* Method to delete a item by id
	 * 
	 * @param int id of cartItem
	 * 
	 * */
    public void deleteItemCartById(int id) {
        jdbcTemplate.update(
                "DELETE FROM cart_items WHERE id = ?",
                id
        );
    }
    
    /* Method to get a list of CartItem
	 * 
	 * @param int id of cartItem
	 * @return List<CartItem> list of CartItem
	 * */
	@Override
	public List<CartItem> findItemById(int id) {
		return jdbcTemplate.query("SELECT * FROM cart_items WHERE id_cart=?",  new CarItemRowMapper(productRepository),id);
	}
	
	/**/
	private static final class CarItemRowMapper implements RowMapper<CartItem>{
		private final IProductRepository productRepository;
		
		public CarItemRowMapper(IProductRepository productRepository) {
			this.productRepository =productRepository;
		}
		/* Method to get a records from a row in a table
		 * @param ResultSet rs, int rowNum Resultset from record
		 * @return  CartItem get a object
		 * */
		@Override
		public CartItem mapRow(ResultSet rs, int rowNum) throws SQLException {
			CartItem item = new CartItem();
			Cart cart = new Cart();
			cart.setId(  rs.getInt("id_cart"));
			item.setCart(cart);
			Product prod = productRepository.findById(rs.getInt("id_product"));
			item.setProduct(prod);
			item.setQuantity( rs.getInt("quantity"));
			return item;
		}
		
	}

	@Override
	public void updateQuantityItemCartById(RqCartItem rqCartItem) {
		 jdbcTemplate.update(
	                "UPDATE cart_items SET quantity =?  WHERE id = ?",
	                rqCartItem.getQuantity(),rqCartItem.getId()
	        );
		
	}
	

}
