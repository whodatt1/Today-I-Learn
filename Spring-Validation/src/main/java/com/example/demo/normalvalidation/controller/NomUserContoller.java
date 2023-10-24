package com.example.demo.normalvalidation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.normalvalidation.dto.NomUser;

@Controller
@RequestMapping("/nomval")
public class NomUserContoller {
	
	@GetMapping("/user")
	public String getNomAddPage(NomUser nomUser) {
		return "add_user_nom";
	}
	
	@PostMapping("/user")
	public String nomUser(@RequestBody NomUser nomUser) {
		System.out.println(nomUser.toString());
		
		boolean flag = true;
		
		if (nomUser.getAge() >= 90) {
			flag = false;
		}
		
		if (nomUser.getEmail() == null || nomUser.getEmail().length() == 0) {
			flag = false;
		}
		
		if (nomUser.getPassword() == null || nomUser.getPassword().length() == 0) {
			flag = false;
		}
		
		if (nomUser.getName() == null || nomUser.getName().length() == 0) {
			flag = false;
		}
		
		if (!flag) {
			return "add_user_nom";
		} else {
			return "complete";
		}
		
	}
}
