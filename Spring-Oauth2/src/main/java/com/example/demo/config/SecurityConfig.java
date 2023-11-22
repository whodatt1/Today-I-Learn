package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.demo.security.AuthEntryPoint;

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
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	/* 작성중
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.cors()
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
			.authorizeRequests()
			.antMatchers("/auth/**", "/oauth2/**").permitAll() // Security 허용 URL
			.anyRequest().authenticated() // 그 외엔 인증이 필요
			.and()
			.oauth2Login()
			.authorizationEndpoint().baseUri("/oauth2/authorization") // 소셜 로그인 URL
			
	}
	*/
}
