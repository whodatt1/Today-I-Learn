package com.example.redis.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.redis.dto.Movie;
import com.example.redis.exception.movie.MovieExistsException;
import com.example.redis.repo.RedisTemplateRepository;
import com.example.redis.service.RedisTemplateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisTemplateServiceImpl implements RedisTemplateService {
	
	private final RedisTemplate<String, Movie> redisTemplate;
	
	private final RedisTemplateRepository redisTemplateRepository;
	
	// Redis Hash Type 사용
	private final HashOperations<String, String, Movie> hashOperations;
	
	@Override
	public Movie insertMovieWithTemp(Movie movie) throws MovieExistsException {
		// DB 데이터 체크
		Optional<Movie> movieChkDB = redisTemplateRepository.findById(movie.getMovieCd());
		// Redis 데이터 체크
		Optional<Movie> movieChkRedis = Optional.ofNullable(hashOperations.get("Movie", movie.getMovieCd()));
		
		if (movieChkDB.isPresent() && movieChkRedis.isPresent()) {
			throw new MovieExistsException("Redis와 DB에 이미 존재하는 영화입니다.");
		} else if (movieChkDB.isPresent() && !movieChkRedis.isPresent()) {
			hashOperations.putIfAbsent("Movie", movie.getMovieCd(), movie);
		} else if (!movieChkDB.isPresent() && movieChkRedis.isPresent()) {
			redisTemplateRepository.save(movie);
		} else {
			hashOperations.putIfAbsent("Movie", movie.getMovieCd(), movie);
			redisTemplateRepository.save(movie);
		}
		
		return movie;
	}

	@Override
	public Movie updateMovieWithTemp(Movie movie) {
		// DB 데이터 체크
		Optional<Movie> movieChkDB = redisTemplateRepository.findById(movie.getMovieCd());
		// Redis 데이터 체크
		Optional<Movie> movieChkRedis = Optional.ofNullable(hashOperations.get("Movie", movie.getMovieCd()));
		
		
		
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
