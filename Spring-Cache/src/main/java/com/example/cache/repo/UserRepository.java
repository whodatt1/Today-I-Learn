package com.example.cache.repo;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Repository;

import com.example.cache.dto.User;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@CacheConfig(cacheNames = "users")
public class UserRepository {
	
	private final Map<Long, User> store = new LinkedHashMap<>();
	
	// users::all 이라는 key 값에 Users 데이터가 저장됩니다.
	// 이후 한번 더 조회하면 users::all을 확인 후 데이터가 있다면 그 데이터를 바로 리턴
	@Cacheable(key = "'all'")
	public List<User> findAll() {
		List<User> users = store.values().stream().toList();
		log.info("Repository findAll {}", users);
		return users;
	}
	
	// userId를 키값으로 설정하여 unless = "#result == null" 추가하여 없는 데이터의 경우 캐싱하지 않음
	// 이 조건을 추가하지 않으면 null 값도 캐싱이 됩니다.
	// 캐시 설정에서 cacheManager.setAllowNullValues(false); 를 추가했기 떄문에 null 값을 캐싱하려고 하면 에러가 나기떄문에 위의 조건 추가하여야함
	@Cacheable(key = "#userId", unless = "#result == null")
	public User findById(Long userId) {
		User user = store.get(userId);
		log.info("Repository find {}", user);
		return user;
	}
	
	// save & update
	// 새로운 데이터를 저장하거나 기존 데이터를 업데이트할때 해당 키값을 캐싱
	// 리스트 값이 갱신되어서 all 전체 조회 캐시를 갱신
	@CachePut(key = "#user.id")
	@CacheEvict(key = "'all'")
	public User save(User user) {
		Long newId = generateId();
		user.setId(newId);
		
		log.info("Repository save {}", user);
		
		store.put(user.getId(), user);
		
		return user;
	}
	
	@CachePut(key = "#user.id")
	@CacheEvict(key = "'all'")
	public User update(User user) {
		System.out.println(user.toString());
		log.info("Repository update {}", user);
		store.put(user.getId(), user);
		return user;
	}
	
	private Long generateId() {
		if (store.isEmpty()) {
			return 1L;
		}
		
		int lastIndex = store.size() -1;
		return (Long) store.keySet().toArray()[lastIndex] + 1;
	}
	
	// User와 Users 데이터 캐싱을 모두 없애줌
	@Caching(evict = {
			@CacheEvict(key = "'all'"),
			@CacheEvict(key = "#user.id")
	})
	public void delete(User user) {
		log.info("Repository delete {}", user);
		store.remove(user.getId());
	}
	
}
