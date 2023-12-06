
package com.example.demo.dto;

import java.util.Map;

import org.springframework.security.oauth2.core.OAuth2AuthenticationException;

import com.example.demo.entity.AuthProvider;

public class OAuth2UserInfoFactory {
	
	public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
		if (registrationId.equalsIgnoreCase(AuthProvider.kakao.toString())) {
			return new KakaoOAuth2UserInfo(attributes);
		} else {
			throw new OAuth2AuthenticationException("지원 하지 않는 로그인 타입 : " + registrationId);
		}
	}
}
