package com.example.redis.ctrl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.redis.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/redis")
@RestController
@RequiredArgsConstructor
public class ProductContoller {
	
	private final ProductService productService;
}
