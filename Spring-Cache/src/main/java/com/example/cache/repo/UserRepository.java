package com.example.cache.repo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Repository;

import com.example.cache.dto.User;
import com.example.cache.dto.Users;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@CacheConfig(cacheNames = "users")
public class UserRepository {
	
	private final Map<Long, User> store = new LinkedHashMap<>();
	
	// users::all 이라는 key 값에 Users 데이터가 저장됩니다.
	// 이후 한번 더 조회하면 users::all을 확인 후 데이터가 있다면 그 데이터를 바로 리턴
	@Cacheable(key = "'all'")
	public Users findAll() {
		List<User> users = store.values().stream().toList();
		log.info("Repository findAll {}", users);
		return new Users(users);
	}
}
