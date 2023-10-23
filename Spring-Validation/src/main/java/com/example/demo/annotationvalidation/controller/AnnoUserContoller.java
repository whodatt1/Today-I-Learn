package com.example.demo.annotationvalidation.controller;

import javax.validation.Valid;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.annotationvalidation.dto.AnnoUser;

@RestController
@RequestMapping("/annouser")
public class AnnoUserContoller {
	
	@GetMapping("/user")
	public String getNomAddPage(AnnoUser annoUser) {
		return "add_user_anno";
	}
	
	@PostMapping("/user") // 2
    public String addUser(@Valid AnnoUser annoUser, BindingResult result){ 

        if(result.hasErrors()){
            return "add_user";
        }

        return "complete";

    }
}
