package com.example.redis.service;

import java.util.List;

import com.example.redis.dto.Product;

public interface RedisCacheManagerService {
	Product insertProductWithCM(Product product);
	Product updateProductWithCM(Product product);
	void deleteProductWithCM(String productCd);
	List<Product> getProductListAllWithCM();
	Product getProductDetailByIdWithCM(String productCd);
}
