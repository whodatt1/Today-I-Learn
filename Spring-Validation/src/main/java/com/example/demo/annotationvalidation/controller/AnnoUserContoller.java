package com.example.demo.annotationvalidation.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.annotationvalidation.dto.AnnoUser;

@Controller
@RequestMapping("/annouser")
public class AnnoUserContoller {
	
	@GetMapping("/user")
	public String getAnnoAddPage(AnnoUser annoUser) {
		return "add_user_anno";
	}
	
	@PostMapping("/user")
    public String addAnnoUser(@Valid AnnoUser annoUser, BindingResult result){ 

		
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
        if(result.hasErrors()){
            return "add_user_anno";
        }

        return "complete";

    }
}
