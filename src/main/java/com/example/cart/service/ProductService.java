package com.example.cart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cart.model.Product;
import com.example.cart.model.RqProduct;
import com.example.cart.repository.IProductRepository;

@Service
public class ProductService implements IProductService{
	
	@Autowired
	private IProductRepository productRepository;
	
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(int id) {
        return productRepository.findById(id);
    }

    public void save(RqProduct producto) {
    	productRepository.save(producto);
    }

    public void update(RqProduct producto,int id) {
    	productRepository.update(producto,id);
    }

    public void delete(int id) {
    	productRepository.deleteById(id);
    }

	
	


}
