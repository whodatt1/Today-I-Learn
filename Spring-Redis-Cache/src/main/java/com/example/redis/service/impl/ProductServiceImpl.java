package com.example.redis.service.impl;

import java.util.List;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.redis.dto.Product;
import com.example.redis.service.ProductService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	
	private final RedisTemplate<String, Object> redisTemplate;

	@Override
	public int insertProduct(Product product) {
		return 0;
	}

	@Override
	public int updateProduct(Product product) {
		return 0;
	}

	@Override
	public int deleteProduct(Product product) {
		return 0;
	}

	@Override
	public List<Product> getProductListAll() {
		return null;
	}

	@Override
	public Product getProductDetailById(Long productId) {
		return null;
	}
	
}
