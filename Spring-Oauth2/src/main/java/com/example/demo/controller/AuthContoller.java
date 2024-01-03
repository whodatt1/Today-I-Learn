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
	public String token(@RequestParam String token, @RequestParam String error) {
		if (StringUtils.isNotBlank(error)) {
			return error;
		} else {
			return token;
		}
	}
}
