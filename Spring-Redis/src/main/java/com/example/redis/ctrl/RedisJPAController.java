package com.example.redis.ctrl;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.redis.dto.Product;
import com.example.redis.service.RedisJPAService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//Redis JPA 이용

@Slf4j
@RequestMapping("/redisjpa")
@RestController
@RequiredArgsConstructor
public class RedisJPAController {
	
	private final RedisJPAService redisJPAService;
	
	@PostMapping("/ins")
	public Product insertProductWithJPA(Product product) {
		Product newProduct = redisJPAService.insertProductWithJPA(product);
		log.info("Controller insertProductWithJPA {}", newProduct);
		return newProduct;
	}
	
	
}
