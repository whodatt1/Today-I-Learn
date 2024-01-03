package com.example.demo.security;

import static com.example.demo.security.HttpCookieOAuth2AuthorizationRequestRepository.REDIRECT_URL_PARAM_COOKIE_NAME;

import java.io.IOException;
import java.net.URI;
import java.util.Optional;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;

import com.example.demo.config.AppProperties;
import com.example.demo.exception.BadRequestException;
import com.example.demo.util.CookieUtils;
import com.example.demo.util.TokenProvider;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

// 소셜 인증에 성공 했을때 Handler
@Slf4j
@Component
@RequiredArgsConstructor
public class OAuth2AuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	private final TokenProvider tokenProvider;
	
	private final AppProperties appProperties;
	
	private final HttpCookieOAuth2AuthorizationRequestRepository httpCookieOAuth2AuthorizationRequestRepository;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		String targetUrl = determineTargetUrl(request, response, authentication);
		
		// Http 응답이 이미 보내졌다면
		if (response.isCommitted()) {
			log.debug("reponse has already been commited. unable to redirect to " + targetUrl);
			return;
		}
		log.info("gggggggggggggggggggggggggggggggggggggggggg");
		log.info(targetUrl);
		clearAuthenticationAttributes(request, response);
		getRedirectStrategy().sendRedirect(request, response, targetUrl);
	}
	
	protected String determineTargetUrl(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		Optional<String> redirectUri = CookieUtils.getCookie(request, REDIRECT_URL_PARAM_COOKIE_NAME).map(Cookie::getValue);
		
		if (redirectUri.isPresent() && !isAuthenticationRedirectUri(redirectUri.get())) {
			throw new BadRequestException("unauthorized Redirect URI");
		}
		
		String targetUri = redirectUri.orElse(getDefaultTargetUrl());
		String token = tokenProvider.createToken(authentication);
		return UriComponentsBuilder.fromUriString(targetUri)
				.queryParam("error", "")
				.queryParam("token", token)
				.build().toUriString();
	}
	
	// 쿠키 제거
	protected void clearAuthenticationAttributes(HttpServletRequest request, HttpServletResponse response) {
		super.clearAuthenticationAttributes(request);
		httpCookieOAuth2AuthorizationRequestRepository.removeAuthorizationRequest(request, response);
	}
	
	// RedirectUri 인증정보 검사
	private boolean isAuthenticationRedirectUri(String uri) {
		URI clientRedirectUri = URI.create(uri);
		
		return appProperties.getOauth2().getAuthorizedRedirectUris()
				.stream()
				.anyMatch(authorizedRedirectUri -> {
					URI authorizedURI = URI.create(authorizedRedirectUri);
					if (authorizedURI.getHost().equalsIgnoreCase(clientRedirectUri.getHost()) && authorizedURI.getPort() == clientRedirectUri.getPort()) {
						return true;
					}
					
					return false;
				}); 
	}
}
