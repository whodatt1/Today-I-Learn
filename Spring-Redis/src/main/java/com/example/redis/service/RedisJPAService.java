package com.example.redis.service;

import java.util.List;

import com.example.redis.dto.Product;

public interface RedisJPAService {
	
	Product insertProductWithJPA(Product product);
	Product updateProductWithJPA(Product product);
	Product deleteProductWithJPA(Product product);
	List<Product> getProductListAllWithJPA();
	Product getProductDetailByIdWithJPA(Long productId);
}
