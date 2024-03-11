package com.example.redis.exception.movie;

public class MovieExistsException extends RuntimeException {
	
	public MovieExistsException(String msg, Throwable cause) {
		super(msg, cause);
	}
	
	public MovieExistsException(String msg) {
		super(msg);
	}
}
