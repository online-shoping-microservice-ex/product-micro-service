package com.online.microservice.productservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.online.microservice.productservice.entity.Product;
import com.online.microservice.productservice.exception.ProductNotFoundException;
import com.online.microservice.productservice.service.ProductService;

@RestController
@RequestMapping(path = "/product")
public class ProductCotroller {

	@Autowired
	ProductService productService;

	@GetMapping
	public List<Product> getAllProducts() {
		return productService.findAllProducts();
	}

	@PostMapping(produces = { MediaType.TEXT_PLAIN_VALUE })
	@ResponseStatus(value = HttpStatus.CREATED)
	public void addProduct(@RequestBody Product product) {
		productService.saveProduct(product);

	}

	@DeleteMapping(path = "/{productId}")
	public ResponseEntity<String> deleteProduct(@PathVariable String productId) {
		productService.deleteProdcuct(productId);
		return new ResponseEntity<String>(String.format("Product %1$s deleted successfully", ""), HttpStatus.OK);
	}

	@PutMapping(path = "/{productId}")
	public ResponseEntity<String> updateProduct(@RequestBody Product product, @PathVariable String productId) {
		productService.updateProduct(product);
		return new ResponseEntity<String>(String.format("Product %1$s updated successfully", ""), HttpStatus.OK);
	}

	@ExceptionHandler
	public ResponseEntity<String> productNotFoundExceptionHandler(ProductNotFoundException e) {
		return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
	}

}
