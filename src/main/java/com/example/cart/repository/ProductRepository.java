package com.example.cart.repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import org.springframework.stereotype.Repository;

import com.example.cart.dto.RqProduct;
import com.example.cart.model.Product;

import lombok.extern.slf4j.Slf4j;
@Slf4j
@Repository
public class ProductRepository implements  IProductRepository{

	/*dependency injection to jdbcTemplate */
    @Autowired
    private JdbcTemplate jdbcTemplate;
    
   
  
    /*
	 * (no-javadoc)
	 * @ see  com.example.cart.repository.IProductRepository#findAll()
	 * */
    public List<Product> findAll() {
        return jdbcTemplate.query("SELECT * FROM products",
                new BeanPropertyRowMapper<>(Product.class));
    }

    /*
	 * (no-javadoc)
	 * @ see  com.example.cart.repository.IProductRepository#findById(int id)
	 * */
    public Product findById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM products WHERE id = ?",
        		 new BeanPropertyRowMapper<>(Product.class),
                id);
    }

    /*
	 * (no-javadoc)
	 * @ see  com.example.cart.repository.IProductRepository#save(com.example.cart.dto.RqProduct)
	 * */
	public int save(RqProduct product) {
		try {
			KeyHolder keyHolder = new GeneratedKeyHolder();

			jdbcTemplate.update(connection -> {
				PreparedStatement ps = connection.prepareStatement(
						"INSERT INTO products (name, price, description) VALUES (?, ?, ?)",
						Statement.RETURN_GENERATED_KEYS);
				ps.setString(1, product.getName());
				ps.setDouble(2, product.getPrice());
				ps.setString(3, product.getDescription());
				return ps;
			}, keyHolder);
			return (int) Optional.ofNullable(keyHolder.getKey()).orElse(0);
		} catch (DataAccessException ex) {
				log.info("Exception durig excecution of create"+ex);
				return 0;
		}
	}

	/*
	 * (no-javadoc)
	 * @ see  com.example.cart.repository.IProductRepository#update(com.example.cart.dto.RqProduct,int)
	 * */
    public void update(RqProduct product,int id) {
        jdbcTemplate.update(
                "UPDATE products SET name = ?, price = ?, description=? WHERE id = ?",
                product.getName(), product.getPrice(), product.getDescription(),id
        );
    }
    
    /*
	 * (no-javadoc)
	 * @ see  com.example.cart.repository.IProductRepository#updateProductStock(com.example.cart.model.Product)
	 * */
    public void updateProductStock(Product product) {
        jdbcTemplate.update(
                "UPDATE products SET   stock=? WHERE id = ?",
                product.getStock(),product.getId()
        );
    }

    /*
	 * (no-javadoc)
	 * @ see  com.example.cart.repository.IProductRepository#deleteById(int)
	 * */
    public void deleteById(int id) {
        jdbcTemplate.update(
                "DELETE FROM products WHERE id = ?",
                id
        );
    }

	
	

	
}
