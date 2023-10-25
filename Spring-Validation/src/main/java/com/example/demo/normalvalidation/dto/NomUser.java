package com.example.demo.normalvalidation.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Data
public class NomUser {
	private String email;
	
	private String password;
	
	private int age;
	
	private String name;
}
