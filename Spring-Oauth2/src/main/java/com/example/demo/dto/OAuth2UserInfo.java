package com.example.demo.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public abstract class OAuth2UserInfo {
	
	// 해당 플랫폼으로부터 받는 정보
	protected Map<String, Object> attributes;
	
	public abstract String getId();
	public abstract String getEmail();
	public abstract String getName();
	public abstract String getImageUrl();
}
