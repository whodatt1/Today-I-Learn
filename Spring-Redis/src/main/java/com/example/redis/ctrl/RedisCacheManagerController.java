
package com.example.redis.ctrl;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.redis.dto.User;
import com.example.redis.service.RedisCacheManagerService;

import lombok.RequiredArgsConstructor;

//Redis CacheManager 이용

@RestController
@RequiredArgsConstructor
public class RedisCacheManagerController {
	
	private final RedisCacheManagerService redisCacheManagerService;
	
	@GetMapping("/rediscm")
	public ModelAndView rediscm() {
		ModelAndView rediscm = new ModelAndView("RC_main");
		return rediscm;
	}
	
	@PostMapping("/rediscm/ins")
	public User insertUserWithCM(User user) {
		User newUser = redisCacheManagerService.insertUserWithCM(user);
		return newUser;
	}
	
	@PutMapping("/rediscm/upd")
	public User updateUserWithCM(User user) {
		User updUser = redisCacheManagerService.updateUserWithCM(user);
		return updUser;
	}
	
	@DeleteMapping("/rediscm/del/{userId}")
	public void deleteUserWithCM(@PathVariable String userId) {
		redisCacheManagerService.deleteUserWithCM(userId);
	}
	
	@GetMapping("/rediscm/all")
	public List<User> getUserListAllWithCM() {
		List<User> uList = redisCacheManagerService.getUserListAllWithCM();
		return uList;
	}
	
	@GetMapping("/rediscm/detail/{userId}")
	public User getUserDetailByIdWithCM(@PathVariable String userId) {
		User detUser = redisCacheManagerService.getUserDetailByIdWithCM(userId);
		return detUser;
	}
}
