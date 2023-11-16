package com.example.demo.annotationvalidation.dto;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class AnnoUser {
	@NotBlank(message = "이메일을 입력해주세요.")
	@Email
	private String email;
	
	@NotBlank(message = "패스워드를 입력해주세요.")
	@Pattern(regexp="[a-zA-Z1-9]{8,12}", message = "비밀번호는 대 소문자 영어와 숫자를 포함해서 8~12자리 이내로 입력해주세요.")
	private String password;
	
	@Max(value = 90)
	private int age;
	
	@NotBlank(message = "이름을 입력해주세요.")
	@Size(min = 2, max = 8, message = "이름을 2~8자 사이로 입력해주세요.")
	private String name;
}