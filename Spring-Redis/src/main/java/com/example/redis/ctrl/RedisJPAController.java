package com.example.redis.ctrl;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.redis.dto.Product;
import com.example.redis.service.RedisJPAService;

import lombok.RequiredArgsConstructor;

//Redis JPA 이용

@RequestMapping("/redisjpa")
@RestController
@RequiredArgsConstructor
public class RedisJPAController {
	
	private final RedisJPAService redisJPAService;
	
	@PostMapping("/ins")
	public Product insertProductWithJPA(Product product) {
		Product newProduct = redisJPAService.insertProductWithJPA(product);
		return newProduct;
	}
	
	@PutMapping("/upd")
	public Product updateProductWithJPA(Product product) {
		Product updProduct = redisJPAService.updateProductWithJPA(product);
		return updProduct;
	}
	
	@DeleteMapping("/del/{productCd}")
	public void deleteProductWithJPA(@PathVariable String productCd) {
		redisJPAService.deleteProductWithJPA(productCd);
	}
	
	@GetMapping("/all")
	public List<Product> getProductListAllWithJPA() {
		List<Product> pList = redisJPAService.getProductListAllWithJPA();
		return pList;
	}
	
	@GetMapping("/detail/{productCd}")
	public Product getProductDetailByIdWithJPA(@PathVariable String productCd) {
		Product detProduct = redisJPAService.getProductDetailByIdWithJPA(productCd);
		return detProduct;
	}
}
