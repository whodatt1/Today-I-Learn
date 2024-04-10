package com.example.redis.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.example.redis.dto.User;
import com.example.redis.exception.user.UserExistsException;
import com.example.redis.exception.user.UserNotFoundException;
import com.example.redis.repo.RedisCacheManagerRepository;
import com.example.redis.service.RedisCacheManagerService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisCacheManagerServiceImpl implements RedisCacheManagerService {
	
	private final RedisCacheManagerRepository redisCacheManagerRepository;

	@Override
	// cacheManger 속성에 bean으로 등록된 cacheManger 명시
	@CachePut(value = "User", key = "#user.userId", cacheManager = "cacheManager")
	public User insertUserWithCM(User user) throws UserExistsException {
		Optional<User> userChk = redisCacheManagerRepository.findById(user.getUserId());
		if(userChk.isPresent()) {
			throw new UserExistsException("이미 존재하는 유저입니다!");
		}
		
		return redisCacheManagerRepository.save(user);
	}

	@Override
	@CachePut(value = "User", key = "#user.userId", cacheManager = "cacheManager")
	public User updateUserWithCM(User user) throws UserNotFoundException {
		Optional<User> userChk = Optional.ofNullable(redisCacheManagerRepository.findById(user.getUserId()))
				.orElseThrow(() -> new UserNotFoundException("해당 유저를 찾을 수 없습니다!"));
		
		if(userChk.isPresent()) {
			user = redisCacheManagerRepository.save(user);
		}
		
		return user;
	}

	@Override
	@CachePut(value = "User", key = "#user.userId", cacheManager = "cacheManager")
	@Caching(evict = {
			@CacheEvict(value = "User", key =  "#userId", cacheManager = "cacheManager"),
			@CacheEvict(value = "User", key =  "'allY'", cacheManager = "cacheManager"),
			@CacheEvict(value = "User", key =  "'allN'", cacheManager = "cacheManager")
	})
	public User deleteUserWithCM(User user) throws UserNotFoundException {
		Optional<User> userChk = Optional.ofNullable(redisCacheManagerRepository.findById(user.getUserId()))
				.orElseThrow(() -> new UserNotFoundException("해당 유저를 찾을 수 없습니다!"));
		
		if(userChk.isPresent()) {
			user = redisCacheManagerRepository.save(user);
		}
		
		return user;
	}

	@Override
	@Cacheable(value = "User", key = "'all' + #params['delYn']", cacheManager = "cacheManager") // 테스트하면서 문제 없는지 확인
	public List<User> getUserListAllWithCM(HashMap<String, Object> params) {
		
		Iterable<User> all = redisCacheManagerRepository.findAllByDelYn(params);
		
		log.info("ServiceImpl getList {}", all);
		
		List<User> uList = new ArrayList<>();
		
		all.forEach(uList::add);
		
		return uList;
	}

	@Override
	@Cacheable(value = "User", key = "#userId", unless = "#result == null", cacheManager = "cacheManager")
	public User getUserDetailByIdWithCM(String userId) throws UserNotFoundException {
		Optional<User> userChk = Optional.ofNullable(redisCacheManagerRepository.findById(userId))
				.orElseThrow(() -> new UserNotFoundException("해당 유저를 찾을 수 없습니다!"));
		
		log.info("ServiceImpl getUserOne {}", userChk.get());
		
		return userChk.get();
	}

}
