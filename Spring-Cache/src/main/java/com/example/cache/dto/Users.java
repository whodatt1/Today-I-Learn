package com.example.cache.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@NoArgsConstructor
public class Users {
	private List<User> users = new ArrayList<>();
	
	public Users(List<User> users) {
		this.users = users;
	}
}
