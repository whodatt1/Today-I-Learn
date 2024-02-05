package com.example.cache.ctrl;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.cache.dto.User;
import com.example.cache.repo.UserRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/cache")
@RequiredArgsConstructor
public class UserController {
	
	private final UserRepository userRepository;
	
	@GetMapping("/main")
	public String main() {
		return "main";
	}
	
	@GetMapping("/users")
	@ResponseBody
	public List<User> findAll() {
		List<User> users = userRepository.findAll();
		log.info("Controller findAll {}", users);
		return users;
	}
	
	@GetMapping("/user/{userId}")
	@ResponseBody
	public User findById(@PathVariable String userId) {
		Long realUserId = Long.parseLong(userId);
		User user = userRepository.findById(realUserId);
		log.info("Controller findById {}", user);
		return user;
	}
	
	@PostMapping("/user/ins")
	@ResponseBody
	public User save(@RequestBody User user) {
		User newUser = userRepository.save(user);
		log.info("Controller save {}", newUser);
		return newUser;
	}
	
	@PutMapping("/user/mod")
	@ResponseBody
	public User update(@RequestBody User user) {
		User modUser = userRepository.update(user);
		log.info("Controller update {}", modUser);
		return modUser;
	}
	
	@DeleteMapping("/user/{userId}")
	@ResponseBody
	public void delete(@PathVariable String userId) {
		Long realUserId = Long.parseLong(userId);
		User delUser = userRepository.findById(realUserId);
		userRepository.delete(delUser);
		log.info("Controller delete {}", delUser);
	}
}
