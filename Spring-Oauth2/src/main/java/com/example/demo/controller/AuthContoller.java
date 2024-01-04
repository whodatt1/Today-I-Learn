package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nimbusds.oauth2.sdk.util.StringUtils;

@RestController
@RequestMapping("/auth")
public class AuthContoller {
	
	@GetMapping("/token")
	public String token(String code) {
		return "인증 완료 코드값 : " + code;
	}
}
