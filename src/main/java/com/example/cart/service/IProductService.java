package com.example.cart.service;

import java.util.List;

import com.example.cart.model.Product;
import com.example.cart.model.RqProduct;

public interface IProductService {
	 public List<Product> findAll();
	 public Product findById(int id);
	 public void save(RqProduct producto);
	 public void update(RqProduct producto, int id);
	 public void delete(int id);
}
