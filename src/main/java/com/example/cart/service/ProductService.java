package com.example.cart.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.cart.dto.RqProduct;
import com.example.cart.model.Product;
import com.example.cart.repository.IProductRepository;

@Service
public class ProductService implements IProductService{
	
	/* dependency injection to productRepository*/
	@Autowired
	private IProductRepository productRepository;
	
	/*
	 * (no-javadoc)
	 * @ see  com.example.cart.service.IProductService#findAll()
	 * */
    public List<Product> findAll() {
        return productRepository.findAll();
    }
    
    /*
	 * (no-javadoc)
	 * @ see  com.example.cart.service.IProductService#findById(int)
	 * */
    public Product findById(int id) {
        return productRepository.findById(id);
    }
    
    /*
	 * (no-javadoc)
	 * @ see  com.example.cart.service.IProductService#save(com.example.cart.dto.RqProduct)
	 * */
    public void save(RqProduct producto) {
    	productRepository.save(producto);
    }
    
    /*
   	 * (no-javadoc)
   	 * @ see  com.example.cart.service.IProductService#update(com.example.cart.dto.RqProduct,int)
   	 * */
    public void update(RqProduct producto,int id) {
    	productRepository.update(producto,id);
    }
    
    /*
   	 * (no-javadoc)
   	 * @ see  com.example.cart.service.IProductService#delete(int)
   	 * */
    public void delete(int id) {
    	productRepository.deleteById(id);
    }

	
	


}
