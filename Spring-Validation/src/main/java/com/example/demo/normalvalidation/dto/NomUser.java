package com.example.demo.normalvalidation.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class NomUser {
	private String email;
	
	private String password;
	
	private int age;
	
	private String name;
}
