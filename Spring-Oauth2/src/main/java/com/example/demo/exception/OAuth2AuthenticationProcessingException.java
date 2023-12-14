package com.example.demo.exception;

import org.springframework.security.core.AuthenticationException;

// 인증 중 오류 발생 시
public class OAuth2AuthenticationProcessingException extends AuthenticationException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OAuth2AuthenticationProcessingException(String msg, Throwable t) {
		super(msg, t);
	}
	
	public OAuth2AuthenticationProcessingException(String msg) {
		super(msg);
	}
}
