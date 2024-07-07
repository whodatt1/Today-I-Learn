package com.example.redis.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.redis.dto.User;

@Repository
public interface RedisCacheManagerRepository extends JpaRepository<User, String> {
}
