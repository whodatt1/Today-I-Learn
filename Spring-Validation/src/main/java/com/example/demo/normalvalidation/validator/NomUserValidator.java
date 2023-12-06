package com.example.demo.normalvalidation.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import com.example.demo.normalvalidation.dto.NomUser;

@Component
public class NomUserValidator {
	
	public void validate(NomUser nomUser, BindingResult result) {
		
		// 정규식 검사 객체
		Matcher matcher;
		
		// 비밀번호 정규식
		final String REGEX = "[a-zA-Z1-9]{8,12}";
		
		if (nomUser.getAge() >= 90) {
			result.addError(new FieldError("nomUser", "age", "나이는 90 보다 작아야 합니다."));
		}
		
		if (nomUser.getEmail() == null || nomUser.getEmail().length() == 0) {
			result.addError(new FieldError("nomUser", "email", "이메일을 입력해주세요."));
		}
		
		if (nomUser.getPassword() == null || nomUser.getPassword().length() == 0) {
			result.addError(new FieldError("nomUser", "password", "패스워드를 입력해주세요."));
		}
		
		matcher = Pattern.compile(REGEX).matcher(nomUser.getPassword());
		if (!matcher.find()) {
			result.addError(new FieldError("nomUser", "password", "비밀번호는 대 소문자 영어와 숫자를 포함해서 8~12자리 이내로 입력해주세요."));
		}
		
		if (nomUser.getName() == null || nomUser.getName().length() == 0) {
			result.addError(new FieldError("nomUser", "name", "이름을 입력해주세요."));
		}
		
		if (nomUser.getName().length() < 2 || nomUser.getName().length() > 8) {
			result.addError(new FieldError("nomUser", "name", "이름을 2~8자 사이로 입력해주세요."));
		}
		
	}
}