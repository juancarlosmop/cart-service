package com.example.cart.repository;

import java.util.List;

import com.example.cart.dto.RqProduct;
import com.example.cart.model.Product;

/*
 * Interface to manage the operations entity Product
 * 
 * @author JC
 * */
public interface IProductRepository {
	
	/* Method to get list from Products
	 * 
	 * @return List<Product> list from Product
	 * */
	 public List<Product> findAll();
	 
	 /* Method to get a product by id
	  * 
	  * @Param int id from product
	  * @return Product 
	  * */
	 public Product findById(int id);
	 
	 /* Method to save a new product
	  * 
	  * @Param RqProduct product request from product
	  * @return int
	  * */
	 public int  save(RqProduct product);
	 
	 /* Method to update a product by id
	  * 
	  * @Param RqProduct product request from product
	  * */
	 public void update(RqProduct product,int id);
	 
	 /* Method to delete a product by id
	  * 
	  * @Param int  id from product
	  * */
	 public void deleteById(int  id);
	 
	 /*Method to update the field stock from proudct
	  * 
	  * @Param Product product Product 
	  * */
	 public void updateProductStock(Product product);
}
