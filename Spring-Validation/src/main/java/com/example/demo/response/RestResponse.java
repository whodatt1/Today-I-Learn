package com.example.demo.response;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class RestResponse {
	
	private boolean success;
	private String message;
	private Object data;
	
	public RestResponse(boolean success, String message) {
		this.success = success;
		this.message = message;
	}
}
