package com.example.redis.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.redis.dto.Movie;

@Repository
public interface RedisTemplateRepository extends CrudRepository<Movie, String> {
}
