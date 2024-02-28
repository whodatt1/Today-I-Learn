package com.example.redis.exception;

public class UserNotFoundException extends RuntimeException {
	
	public UserNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public UserNotFoundException(String msg) {
		super(msg);
	}
}
