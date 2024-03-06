package com.example.redis.service.impl;

import java.util.List;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;

import com.example.redis.dto.User;
import com.example.redis.service.RedisCacheManagerService;

public class RedisCacheManagerServiceImpl implements RedisCacheManagerService {

	@Override
	// cacheManger 속성에 bean으로 등록된 cacheManger 명시
	@CachePut(value = "User", key = "#user.userId", cacheManager = "cacheManager")
	public User insertUserWithCM(User user) {
		return null;
	}

	@Override
	@CachePut(value = "User", key = "#user.userId", cacheManager = "cacheManager")
	public User updateUserWithCM(User user) {
		return null;
	}

	@Override
	@Caching(evict = {
			@CacheEvict(value = "User", key =  "#userId", cacheManager = "cacheManager"),
			@CacheEvict(value = "User", key =  "'all'", cacheManager = "cacheManager")
	})
	public void deleteUserWithCM(String userId) {
		
	}

	@Override
	@Cacheable(value = "User", key = "'all'", cacheManager = "cacheManager")
	public List<User> getUserListAllWithCM() {
		return null;
	}

	@Override
	@Cacheable(value = "User", key = "#userId", unless = "#result == null", cacheManager = "cacheManager")
	public User getUserDetailByIdWithCM(String userId) {
		return null;
	}

}
