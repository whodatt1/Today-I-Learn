package com.example.redis.service;

import java.util.List;

import com.example.redis.dto.Product;

public interface ProductService {
	
	Product insertProduct(Product product);
	int updateProduct(Product product);
	int deleteProduct(Product product);
	List<Product> getProductListAll();
	Product getProductDetailById(Long productId);
}
