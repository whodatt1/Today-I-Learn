package com.example.redis.ctrl;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.redis.dto.Product;
import com.example.redis.service.ProductService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping("/redis")
@RestController
@RequiredArgsConstructor
public class ProductContoller {
	
	private final ProductService productService;
	
	public Product insertProduct(Product product) {
		Product resProduct = productService.insertProduct(product);
		log.info("Controller insertProduct {}", resProduct);
		return resProduct;
	}
	
	
	public Product getProductDetailById(Long productId) {
		Product product = productService.getProductDetailById(productId);
		log.info("Controller insertProduct {}", product);
		return product;
	}
	
	
	public List<Product> getProductListAll() {
		List<Product> pList = productService.getProductListAll();
		log.info("Controller insertProduct {}", pList);
		return pList;
	}
	
	
}
