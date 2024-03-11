package com.example.redis.exception.movie;

public class MovieNotFoundException extends RuntimeException {
	
	public MovieNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public MovieNotFoundException(String msg) {
		super(msg);
	}
}
