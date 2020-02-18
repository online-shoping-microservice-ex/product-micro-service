package com.online.microservice.productservice.service;

import java.util.List;

import org.springframework.data.domain.PageRequest;

import com.online.microservice.productservice.entity.Product;



public interface ProductService {

	Product saveProduct(Product product);

	void updateProduct(Product product);

	void deleteProdcuct(String id);

	List<Product> findAllProducts();
	
	List<Product> findAllProducts(int page, int size);

	Product findById(String id);

	

}
