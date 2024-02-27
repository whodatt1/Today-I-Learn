package com.example.redis.service.impl;

import java.util.List;

import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.example.redis.dto.Product;
import com.example.redis.service.ProductService;
import com.example.redis.util.RedisUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
	
	private final RedisUtils redisUtils;

	@Override
	public Product insertProduct(Product product) {
		redisUtils.setData(null, product, 3L);
		return null;
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
