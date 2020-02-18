package com.online.microservice.productservice.entity;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import lombok.NoArgsConstructor;

@Document
@Data
@NoArgsConstructor
public class Product {

	@Id
	private String id;
	private String name;
	private String description;
	private List<ProductFeature> features;
	private ProductStatus status;

	

}
