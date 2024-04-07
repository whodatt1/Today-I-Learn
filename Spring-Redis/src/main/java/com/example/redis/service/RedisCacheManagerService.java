package com.example.redis.service;

import java.util.HashMap;
import java.util.List;

import com.example.redis.dto.User;

public interface RedisCacheManagerService {
	User insertUserWithCM(User user);
	User updateUserWithCM(User user);
	void deleteUserWithCM(String userId);
	List<User> getUserListAllWithCM(HashMap<String, Object> params);
	User getUserDetailByIdWithCM(String userId);
}
