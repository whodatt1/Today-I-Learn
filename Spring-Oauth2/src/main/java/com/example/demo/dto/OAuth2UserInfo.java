package com.example.demo.dto;

import java.util.Map;

public abstract class OAuth2UserInfo {
	
	protected Map<String, Object> attributes;
	
	public abstract String getId();
	public abstract String getEmail();
	public abstract String getName();
	public abstract String getImageUrl();
}
