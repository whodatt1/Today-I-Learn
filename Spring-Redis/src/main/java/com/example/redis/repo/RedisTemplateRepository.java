package com.example.redis.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.redis.dto.Movie;

public interface RedisTemplateRepository extends CrudRepository<Movie, String> {

}
