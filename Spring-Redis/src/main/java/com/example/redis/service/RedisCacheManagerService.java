package com.example.redis.service;

import java.util.List;

import com.example.redis.dto.User;

public interface RedisCacheManagerService {
	User insertUserWithCM(User user);
	User updateUserWithCM(User user);
	void deleteUserWithCM(User user);
	List<User> getUserListAllWithCM();
	User getUserDetailByIdWithCM(String userId);
}
