
package com.example.redis.ctrl;

import java.util.HashMap;
import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.redis.dto.User;
import com.example.redis.service.RedisCacheManagerService;
import com.example.redis.service.impl.RedisCacheManagerServiceImpl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//Redis CacheManager 이용

@Slf4j
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
	
	@PutMapping("/rediscm/del")
	public User deleteUserWithCM(User user) {
		User delUser = redisCacheManagerService.deleteUserWithCM(user);
		return delUser;
	}
	
	@GetMapping("/rediscm/all")
	public List<User> getUserListAllWithCM(@RequestParam HashMap<String, Object> params) {
		
		List<User> uList = redisCacheManagerService.getUserListAllWithCM(params);
		
		log.info("Contoller getList {}", uList);
		
		return uList;
	}
	
	@GetMapping("/rediscm/detail/{userId}")
	public User getUserDetailByIdWithCM(@PathVariable String userId) {
		User detUser = redisCacheManagerService.getUserDetailByIdWithCM(userId);
		
		log.info("Controller getUserOne {}", detUser);
		
		return detUser;
	}
}
