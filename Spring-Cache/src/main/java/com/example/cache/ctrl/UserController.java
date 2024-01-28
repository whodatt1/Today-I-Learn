package com.example.cache.ctrl;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.cache.dto.User;
import com.example.cache.dto.Users;
import com.example.cache.repo.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/cache")
@RequiredArgsConstructor
public class UserController {
	
	private final UserRepository userRepository;
	
	@GetMapping("/main")
	public String main() {
		return "main";
	}
	
	@GetMapping("/users")
	public Users findAll(Model model) {
		Users users = userRepository.findAll();
		log.info("Contoller findAll {}", users);
		return users;
	}
	
	@GetMapping("/user/${userId}")
	public User findById(@PathVariable String userId) {
		Long realUserId = Long.parseLong(userId);
		User user = userRepository.findById(realUserId);
		log.info("Contoller findById {}", user);
		return user;
	}
	
	
}
