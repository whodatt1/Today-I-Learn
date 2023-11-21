package com.example.demo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;
import lombok.Setter;

// yml에서 app. 로 시작되는 설정 정보들을 객체로 매핑하는 클래스

@Getter
@ConfigurationProperties(prefix = "app")
public class AppProperties {
	private final Auth auth = new Auth();
	private final OAuth2 oauth2 = new OAuth2();
	
	@Getter
	@Setter
	public static class Auth {
		private String tokenSecret;
		private long tokenExpirationTime;
	}
	
	@Getter
	public static class OAuth2 {
		private List<String> authorizedRedirectUris = new ArrayList<>();
		public OAuth2 authorizedRedirectUris(List<String> authorizedRedirectUris) {
			this.authorizedRedirectUris = authorizedRedirectUris;
			return this;
		}
	}
}
