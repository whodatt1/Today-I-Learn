package com.example.demo.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.oauth2.client.web.AuthorizationRequestRepository;
import org.springframework.security.oauth2.core.endpoint.OAuth2AuthorizationRequest;
import org.springframework.stereotype.Component;

import com.example.demo.util.CookieUtils;
import com.nimbusds.oauth2.sdk.util.StringUtils;

// 요청 받은 인증정보를 쿠키에 저장하고 검색
@Component
public class HttpCookieOAuth2AuthorizationRequestRepository implements AuthorizationRequestRepository<OAuth2AuthorizationRequest> {
	
	public static final String OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME = "oauth2_auth_request";
	public static final String REDIRECT_URL_PARAM_COOKIE_NAME = "redirect_uri";
	private static final int cookieExpireSeconds = 180;
	
	@Override
	public OAuth2AuthorizationRequest loadAuthorizationRequest(HttpServletRequest request) {
		OAuth2AuthorizationRequest oAuth2AuthorizationRequest = CookieUtils.getCookie(request, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME)
				.map(cookie -> CookieUtils.deserialize(cookie, OAuth2AuthorizationRequest.class))
				.orElse(null);
		return oAuth2AuthorizationRequest;
	}
	@Override
	public void saveAuthorizationRequest(OAuth2AuthorizationRequest authorizationRequest, HttpServletRequest request,
			HttpServletResponse response) {
		
		if (authorizationRequest == null) {
			removeAuthorizationRequestCookies(request, response);
			return;
		}
		
		CookieUtils.addCookie(response, REDIRECT_URL_PARAM_COOKIE_NAME, CookieUtils.serialize(authorizationRequest), cookieExpireSeconds);
		String redirectAfterLogin = request.getParameter(REDIRECT_URL_PARAM_COOKIE_NAME);
				
		if (StringUtils.isNotBlank(redirectAfterLogin)) {
			
			CookieUtils.addCookie(response, REDIRECT_URL_PARAM_COOKIE_NAME, redirectAfterLogin, cookieExpireSeconds);
		}
		
	}
	@Override
	public OAuth2AuthorizationRequest removeAuthorizationRequest(HttpServletRequest request) {
		return this.loadAuthorizationRequest(request);
	}
	
	public void removeAuthorizationRequestCookies(HttpServletRequest request, HttpServletResponse response) {
		CookieUtils.deleteCookie(request, response, OAUTH2_AUTHORIZATION_REQUEST_COOKIE_NAME);
		CookieUtils.deleteCookie(request, response, REDIRECT_URL_PARAM_COOKIE_NAME);
	}
}
