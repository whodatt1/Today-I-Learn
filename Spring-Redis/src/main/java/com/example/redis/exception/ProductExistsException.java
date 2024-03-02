package com.example.redis.exception;

public class ProductExistsException extends RuntimeException {
	
	public ProductExistsException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public ProductExistsException(String msg) {
		super(msg);
	}
}
