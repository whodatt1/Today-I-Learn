package com.example.redis.service;

import java.util.List;

import com.example.redis.dto.Product;

public interface RedisJPAService {
	
	Product insertProductWithJPA(Product product);
	Product updateProductWithJPA(Product product);
	void deleteProductWithJPA(String productCd);
	List<Product> getProductListAllWithJPA();
	Product getProductDetailByIdWithJPA(String productCd);
	List<Product> getProductListAllOrderByRegAtWithJPA();
}
