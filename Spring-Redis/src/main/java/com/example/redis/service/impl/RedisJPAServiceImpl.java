package com.example.redis.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.redis.dto.Product;
import com.example.redis.exception.UserExistsException;
import com.example.redis.repo.RedisJPARepository;
import com.example.redis.service.RedisJPAService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisJPAServiceImpl implements RedisJPAService {
	
	private final RedisJPARepository redisJpaRepository;

	@Override
	public Product insertProductWithJPA(Product product) throws UserExistsException {
		Optional<Product> productChk = Optional.ofNullable(redisJpaRepository.findById(product.getProductCd())
											   .orElseThrow(() -> new UserExistsException("이미 등록된 사용자입니다.")));
		
		return redisJpaRepository.save(product);
	}

	@Override
	public Product updateProductWithJPA(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product deleteProductWithJPA(Product product) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> getProductListAllWithJPA() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product getProductDetailByIdWithJPA(Long productId) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
