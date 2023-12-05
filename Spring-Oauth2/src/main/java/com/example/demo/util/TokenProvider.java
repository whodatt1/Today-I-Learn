package com.example.demo.util;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.example.demo.config.AppProperties;
import com.example.demo.exception.OAuth2AuthenticationProcessingException;
import com.example.demo.security.UserPrincipal;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import lombok.AllArgsConstructor;

// JWT를 발급하고 인증하는 Util Class
@AllArgsConstructor
@Service
public class TokenProvider {
	
	private AppProperties appProperties;
	
	// 토큰 생성
	public String createToken(Authentication authentication) {
		UserPrincipal userPrincipal = (UserPrincipal) authentication.getPrincipal();
		
		Date now = new Date();
		Date expiryDate = new Date(now.getTime() + appProperties.getAuth().getTokenExpirationTime());
		
		return Jwts.builder()
				   .setSubject(Long.toString(userPrincipal.getId()))
				   .setIssuedAt(new Date())
				   .setExpiration(expiryDate)
				   .signWith(SignatureAlgorithm.HS512, appProperties.getAuth().getTokenSecret())
				   .compact();
	}
	
	// 토큰정보에서 유저 ID 추출
	public Long getUserIdFromToken(String token) {
		Claims claims = Jwts.parser()
						    .setSigningKey(appProperties.getAuth().getTokenSecret())
						    .parseClaimsJws(token)
						    .getBody();
		
		return Long.parseLong(claims.getSubject());
	}
	
	// 토큰 유효성 검사
	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(appProperties.getAuth().getTokenSecret()).parseClaimsJws(token);
			return true;
		} catch (SignatureException e) { // 유효하지 않은 JWT 서명
			throw new OAuth2AuthenticationProcessingException("not valid jwt signature");
		} catch (MalformedJwtException e) { // 유효하지 않은 JWT
			throw new OAuth2AuthenticationProcessingException("not valid jwt");
		} catch (io.jsonwebtoken.ExpiredJwtException e) { // 만료된 JWT
			throw new OAuth2AuthenticationProcessingException("expired jwt");
		}
		
	}
}
