package com.example.redis.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.redis.dto.User;

@Repository
public interface RedisCacheManagerRepository extends CrudRepository<User, String> {
}
