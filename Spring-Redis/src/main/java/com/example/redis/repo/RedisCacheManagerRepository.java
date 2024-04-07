package com.example.redis.repo;

import java.util.HashMap;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.redis.dto.User;

@Repository
public interface RedisCacheManagerRepository extends JpaRepository<User, String> {
	@Query("select u from User u where u.delYn = :#{#params['delYn']}")
	List<User> findAllByDelYn(@Param("params") HashMap<String, Object> params);
}
