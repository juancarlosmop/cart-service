package com.example.cart.repository;

import java.util.List;

import com.example.cart.model.Product;
import com.example.cart.model.RqProduct;

public interface IProductRepository {
	 public List<Product> findAll();
	 public Product findById(int id);
	 public int  save(RqProduct product);
	 public void update(RqProduct product,int id);
	 public void deleteById(int  id);
	 public void updateProductStock(Product product);
}
