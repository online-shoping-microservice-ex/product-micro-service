package com.online.microservice.productservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.stereotype.Service;

import com.online.microservice.productservice.entity.Product;
import com.online.microservice.productservice.exception.ProductNotFoundException;
import com.online.microservice.productservice.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public Product saveProduct(Product product) {
		// TODO Auto-generated method stub

//		if (Objects.isNull(product.getSuppliers()))
//			product.setSuppliers(new ArrayList<Supplier>());
		return productRepository.save(product);
	}

	@Override
	public void updateProduct(Product product) {
		// TODO Auto-generated method stub
		productRepository.save(product);
	}

	@Override
	public void deleteProdcuct(String id) {
		// TODO Auto-generated method stub
		productRepository.deleteById(id);
	}

	@Override
	public List<Product> findAllProducts() {
		// TODO Auto-generated method stub
		Sort sort = Sort.by(Order.desc("id"));
		return productRepository.findAll(sort);
	}

	@Override
	public List<Product> findAllProducts(int page, int size) {
		// TODO Auto-generated method stub
		final Pageable pagebale = PageRequest.of(page, size, Sort.by(Order.by("id")));
		Page<Product> currentPage = productRepository.findAll(pagebale);
		return currentPage.getContent();

	}

	@Override
	public Product findById(String id) {
		// TODO Auto-generated method stub
		return productRepository.findById(id).orElseThrow(() -> {
			return new ProductNotFoundException(String.format("Product not found for given id : %1$s", id));
		});
	}

}
