package com.example.demo.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.util.CookieUtils;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.example.demo.security.HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URL_PARAM_COOKIE_NAME;

// 소설 인증의 실패 시 Handler
@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {
	
	private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String targetUrl = CookieUtils.getCookie(request, REDIRECT_URL_PARAM_COOKIE_NAME)
				.map(Cookie::getValue)
				.orElse(("/"));
		
		targetUrl = UriComponentsBuilder.fromUriString(targetUrl)
				.queryParam("token", "")
				.queryParam("error", exception.getLocalizedMessage())
				.build().toUriString();
		
		httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequest(request, response);
		log.info("gggggggggggggggggggggggggggggggggggggggggg");
		log.info(targetUrl);
		getRedirectStrategy().sendRedirect(request, response, targetUrl);
	}
	
}
