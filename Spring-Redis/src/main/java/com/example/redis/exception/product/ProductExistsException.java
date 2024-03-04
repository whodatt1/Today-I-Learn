package com.example.redis.exception.product;

public class ProductExistsException extends RuntimeException {
	
	public ProductExistsException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public ProductExistsException(String msg) {
		super(msg);
	}
}
