package com.example.demo.normalvalidation.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.normalvalidation.dto.NomUser;
import com.example.demo.normalvalidation.validator.NomUserValidator;

@Controller
@RequestMapping("/nomuser")
public class NomUserContoller {
	
	@Autowired
	private NomUserValidator nomUserValidator;
	
	@GetMapping("/user")
	public String getNomAddPage(NomUser nomUser) {
		return "add_user_nom";
	}
	
	@PostMapping("/user")
	public String nomUser(NomUser nomUser, BindingResult result) {
		
		nomUserValidator.validate(nomUser, result);
		
		StringBuilder builder = new StringBuilder();
		List<FieldError> fieldErrors = result.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
        	builder.append("[");
            builder.append(fieldError.getField());
            builder.append("](은)는 ");
            builder.append(fieldError.getDefaultMessage());
            builder.append(" 입력된 값: [");
            builder.append(fieldError.getRejectedValue());
            builder.append("]");
        }
        
        System.out.println(builder);
		
		if (result.hasErrors()) {
			return "add_user_nom";
		}

		return "complete";
	}
}
