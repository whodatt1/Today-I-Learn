package com.example.redis.repo;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.redis.dto.Product;

@Repository
public interface RedisJPARepository extends CrudRepository<Product, String> {
	List<Product> findAllByOrderByRegAtDesc();
}
