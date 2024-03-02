package com.example.redis.exception;

public class ProductNotFoundException extends RuntimeException {
	
	public ProductNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public ProductNotFoundException(String msg) {
		super(msg);
	}
}
