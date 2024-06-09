
package com.example.redis.ctrl;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.redis.dto.User;
import com.example.redis.service.RedisCacheManagerService;

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
	public ResponseEntity<?> insertUserWithCM(@RequestBody User user) {
		try {
			User newUser = redisCacheManagerService.insertUserWithCM(user);
			
			log.info("Contoller insertUserWithCM");
			
			return new ResponseEntity<>(newUser, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/rediscm/upd")
	public ResponseEntity<?> updateUserWithCM(@RequestBody User user) {
		try {
			User updUser = redisCacheManagerService.updateUserWithCM(user);
			
			log.info("Contoller updateUserWithCM");
			
			return new ResponseEntity<>(updUser, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@DeleteMapping("/rediscm/del")
	public ResponseEntity<?> deleteUserWithCM(@RequestBody User user) {
		try {
			redisCacheManagerService.deleteUserWithCM(user);
			
			log.info("Contoller deleteUserWithCM");
			return new ResponseEntity<>(user, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/rediscm/all")
	public ResponseEntity<?> getUserListAllWithCM() {
		try {
			List<User> uList = redisCacheManagerService.getUserListAllWithCM();
			
			log.info("Contoller getUserListAllWithCM");
			
			return new ResponseEntity<>(uList, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/rediscm/detail/{userId}")
	public ResponseEntity<?> getUserDetailByIdWithCM(@PathVariable String userId) {
		try {
			User detUser = redisCacheManagerService.getUserDetailByIdWithCM(userId);
			
			log.info("Controller getUserDetailByIdWithCM");
			
			return new ResponseEntity<>(detUser, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
		}
	}
}
