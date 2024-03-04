package com.example.redis.service.impl;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;

import com.example.redis.dto.Product;
import com.example.redis.dto.User;
import com.example.redis.service.RedisCacheManagerService;

public class RedisCacheManagerServiceImpl implements RedisCacheManagerService {

	@Override
	// cacheManger 속성에 bean으로 등록된 cacheManger 명시
	public User insertUserWithCM(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User updateUserWithCM(User user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteUserWithCM(String userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Cacheable(value = "User", key = "'all'", cacheManager = "cacheManager")
	public List<User> getUserListAllWithCM() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Cacheable(value = "User", key = "#productCd", unless = "#result == null", cacheManager = "cacheManager")
	public User getUserDetailByIdWithCM(String userId) {
		// TODO Auto-generated method stub
		return null;
	}

}
