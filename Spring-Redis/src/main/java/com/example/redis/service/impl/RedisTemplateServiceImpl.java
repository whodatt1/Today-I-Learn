package com.example.redis.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.redis.dto.Movie;
import com.example.redis.service.RedisTemplateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisTemplateServiceImpl implements RedisTemplateService {
	
	private final RedisTemplate<String, Movie> redisTemplate;
	
	// Redis Hash Type 사용
	private final HashOperations<String, String, Movie> hashOperations;
	
	public RedisTemplateServiceImpl(RedisTemplate<String, Movie> redisTemplate) {
		this.redisTemplate = redisTemplate;
		hashOperations = redisTemplate.opsForHash();
	}
	
	@Override
	public Movie insertMovieWithTemp(Movie movie) {
		hashOperations.put("Movie", movie.getMovieCd(), movie);
		
		Optional<Movie> movieChk = Optional.ofNullable((Movie) hashOperations.get("Movie", movie.getMovieCd()));
		
		if (movieChk.isPresent()) {
			
		} else {
			
		}
		
		return null;
	}

	@Override
	public Movie updateMovieWithTemp(Movie movie) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteMovieWithTemp(String movieCd) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Movie> getMovieListAllWithTemp() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Movie getMovieDetailByIdWithTemp(String movieCd) {
		// TODO Auto-generated method stub
		return null;
	}

}
