package com.example.redis.service.impl;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;

import com.example.redis.dto.Product;
import com.example.redis.service.RedisCacheManagerService;

public class RedisCacheManagerServiceImpl implements RedisCacheManagerService {

	@Override
	public Product insertProductWithCM(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product updateProductWithCM(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteProductWithCM(String productCd) {
		// TODO Auto-generated method stub

	}

	@Override
	// cacheManger 속성에 bean으로 등록된 cacheManger 명시
	@Cacheable(value = "User", key = "'all'", cacheManager = "cacheManager")
	public List<Product> getProductListAllWithCM() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product getProductDetailByIdWithCM(String productCd) {
		// TODO Auto-generated method stub
		return null;
	}

}
