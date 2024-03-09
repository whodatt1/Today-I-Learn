package com.example.redis.ctrl;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.redis.dto.User;
import com.example.redis.service.RedisCacheManagerService;

import lombok.RequiredArgsConstructor;

//Redis CacheManager 이용

@RequestMapping("/rediscm")
@RestController
@RequiredArgsConstructor
public class RedisCacheManagerController {
	
	private final RedisCacheManagerService redisCacheManagerService;
	
	@PostMapping("/ins")
	public User insertUserWithCM(User user) {
		User newUser = redisCacheManagerService.insertUserWithCM(user);
		return newUser;
	}
	
	@PutMapping("/upd")
	public User updateUserWithCM(User user) {
		User updUser = redisCacheManagerService.updateUserWithCM(user);
		return updUser;
	}
	
	@DeleteMapping("/del/{userId}")
	public void deleteUserWithCM(@PathVariable String userId) {
		redisCacheManagerService.deleteUserWithCM(userId);
	}
	
	@GetMapping("/all")
	public List<User> getUserListAllWithCM() {
		List<User> uList = redisCacheManagerService.getUserListAllWithCM();
		return uList;
	}
	
	@GetMapping("/detail/{userId}")
	public User getUserDetailByIdWithCM(@PathVariable String userId) {
		User detUser = redisCacheManagerService.getUserDetailByIdWithCM(userId);
		return detUser;
	}
}
