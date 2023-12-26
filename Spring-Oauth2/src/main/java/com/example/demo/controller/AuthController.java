package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/oauth2")
public class AuthController {
	
	@GetMapping("/callback/kakao")
	public String token() {
		return "return";
	}
	
}
