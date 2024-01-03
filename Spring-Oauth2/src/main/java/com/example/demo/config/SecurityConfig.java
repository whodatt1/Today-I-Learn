package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.demo.security.AuthEntryPoint;
import com.example.demo.security.CustomOAuth2UserService;
import com.example.demo.security.CustomUserDetailsService;
import com.example.demo.security.HttpCookieOAuth2AuthorizationRequestRepository;
import com.example.demo.security.OAuth2AuthenticationFailureHandler;
import com.example.demo.security.OAuth2AuthenticationSuccessHandler;
import com.example.demo.security.TokenAuthenticationFilter;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
@EnableGlobalMethodSecurity(
			securedEnabled = true,
			jsr250Enabled = true,
			prePostEnabled = true
		)
public class SecurityConfig {
	
	private final CustomUserDetailsService customUserDetailsService;
	
	private final CustomOAuth2UserService customOAuth2UserService;
	
	private final OAuth2AuthenticationSuccessHandler oAuth2AuthenticationSuccessHandler;
	
	private final OAuth2AuthenticationFailureHandler oAuth2AuthenticationFailureHandler;
	
	@Bean
	public TokenAuthenticationFilter tokenAuthenticationFilter() {
		return new TokenAuthenticationFilter();
	}
	
	@Bean
	public HttpCookieOAuth2AuthorizationRequestRepository cookieOAuth2AuthorizationRequestRepository() {
		return new HttpCookieOAuth2AuthorizationRequestRepository();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		
		authProvider.setUserDetailsService(customUserDetailsService);
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}
 	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.cors()
			.and()
			.sessionManagement()
			.sessionCreationPolicy(SessionCreationPolicy.STATELESS) // 세션 미사용
			.and()
			.csrf().disable() // csrf 미사용
			.headers().frameOptions().disable()
			.and()
			.formLogin().disable() // 로그인 폼 미사용
			.httpBasic().disable() // Http basic Auth 기반으로 로그인 인증창이 열림 (disable 시 인증창 열리지 않음)
			.exceptionHandling().authenticationEntryPoint(new AuthEntryPoint()) // 인증, 인가가 되지 않은 요청 시 발생
			.and()
			.authorizeHttpRequests()
			.antMatchers("/auth/**", "/oauth2/**").permitAll() // Security 허용 URL
			.anyRequest().authenticated() // 그 외엔 인증이 필요
			.and()
			.oauth2Login()
			.authorizationEndpoint()
			.baseUri("/oauth2/authorization") // 소셜 로그인 URL
			.authorizationRequestRepository(cookieOAuth2AuthorizationRequestRepository()) // 인증 요청을 쿠키에 저장하고 검색
			.and()
			.redirectionEndpoint()
			//.baseUri("/oauth2/callback/*") // 소셜 인증 후 Redirect Url
			.and()
			.userInfoEndpoint().userService(customOAuth2UserService);
		
		http.authenticationProvider(authenticationProvider());
		http.addFilterBefore(tokenAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
		
		return http.build();
	}
}