package com.example.redis.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.redis.dto.Movie;

@Repository
public interface RedisTemplateRepository extends JpaRepository<Movie, String> {
}
