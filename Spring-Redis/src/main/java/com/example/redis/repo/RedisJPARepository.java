package com.example.redis.repo;

import org.springframework.data.repository.CrudRepository;

import com.example.redis.dto.Product;

public interface RedisJPARepository extends CrudRepository<Product, String> {
}
