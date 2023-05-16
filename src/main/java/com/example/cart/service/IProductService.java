package com.example.cart.service;

import java.util.List;

import com.example.cart.dto.RqProduct;
import com.example.cart.model.Product;

public interface IProductService {
	 /* Method to get a products
	  * 
	  * @return List<Product>  list of products
	  * */
	 public List<Product> findAll();
	 
	 /* Method to return a Product
	  * 
	  * @param int id 
	  * @return Product entity
	  * */
	 public Product findById(int id);
	 
	 /* Method to save a new product
	  * 
	  * @param RqProduct product request Product
	  * 
	  * */
	 public void save(RqProduct producto);
	 
	 /* Method to update a product by id
	  * 
	  * @param RqProduct product, int id receive request product an id product
	  * 
	  * */
	 public void update(RqProduct producto, int id);
	 
	 /* Method to delete a product by id
	  * 
	  * @param id of product
	  * 
	  * */
	 public void delete(int id);
}
