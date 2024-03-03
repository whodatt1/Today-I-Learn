package com.example.redis.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.redis.dto.Product;
import com.example.redis.exception.ProductExistsException;
import com.example.redis.exception.ProductNotFoundException;
import com.example.redis.repo.RedisJPARepository;
import com.example.redis.service.RedisJPAService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisJPAServiceImpl implements RedisJPAService {
	
	private final RedisJPARepository redisJpaRepository;

	@Override
	public Product insertProductWithJPA(Product product) throws ProductExistsException {
		Optional<Product> productChk = redisJpaRepository.findById(product.getProductCd());
		
		if (productChk.isPresent()) {
			throw new ProductExistsException("이미 존재하는 품목입니다!");
		}
		
		return redisJpaRepository.save(product); 
	}

	@Override
	public Product updateProductWithJPA(Product product) {
		Optional<Product> productChk = Optional.ofNullable(redisJpaRepository.findById(product.getProductCd())
												.orElseThrow(() -> new ProductNotFoundException("해당 품목을 찾을 수 없습니다!")));
		
		if (productChk.isPresent()) {
			product = redisJpaRepository.save(product);
		}
		
		return product;
	}

	@Override
	public void deleteProductWithJPA(String productCd) {
		Optional<Product> productChk = Optional.ofNullable(redisJpaRepository.findById(productCd)
				.orElseThrow(() -> new ProductNotFoundException("해당 품목을 찾을 수 없습니다!")));
		
		redisJpaRepository.delete(productChk.get());
	}

	@Override
	public List<Product> getProductListAllWithJPA() {
		Iterable<Product> all = redisJpaRepository.findAll();
		
		List<Product> pList = new ArrayList<>();		
		
		all.forEach(pList::add);
		
		return pList;
	}

	@Override
	public Product getProductDetailByIdWithJPA(String productCd) {
		Optional<Product> productChk = Optional.ofNullable(redisJpaRepository.findById(productCd)
				.orElseThrow(() -> new ProductNotFoundException("해당 품목을 찾을 수 없습니다!")));
		
		return productChk.get();
	}
	
	// @Indexed 어노테이션사용으로 regAt 키값으로 인한 정렬 조회
	@Override
	public List<Product> getProductListAllOrderByRegAtWithJPA() {
		Iterable<Product> allOrderByRegAt = redisJpaRepository.findAllByOrderByRegAtDesc();
		
		List<Product> pListOrderByRegAtDesc = new ArrayList<>();
		
		allOrderByRegAt.forEach(pListOrderByRegAtDesc::add);
		
		return pListOrderByRegAtDesc;
	}
}
