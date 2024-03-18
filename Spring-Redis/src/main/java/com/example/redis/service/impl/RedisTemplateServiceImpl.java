package com.example.redis.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Service;

import com.example.redis.dto.Movie;
import com.example.redis.exception.movie.MovieExistsException;
import com.example.redis.exception.movie.MovieNotFoundException;
import com.example.redis.repo.RedisTemplateRepository;
import com.example.redis.service.RedisTemplateService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisTemplateServiceImpl implements RedisTemplateService {
	
	private final String hashReference = "Movie";
	
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
		} else {
			hashOperations.putIfAbsent(hashReference, movie.getMovieCd(), movie);
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
		
		if (!movieChkDB.isPresent() && !movieChkRedis.isPresent()) {
			throw new MovieNotFoundException("Redis와 DB에 존재하지 않는 영화입니다.");
		} else {
			hashOperations.put(hashReference, movie.getMovieCd(), movie);
			redisTemplateRepository.save(movie);
		}
		
		return movie;
	}

	@Override
	public void deleteMovieWithTemp(String movieCd) {
		// DB 데이터 체크
		Optional<Movie> movieChkDB = redisTemplateRepository.findById(movieCd);
		// Redis 데이터 체크
		Optional<Movie> movieChkRedis = Optional.ofNullable(hashOperations.get(hashReference, movieCd));
		
		if (!movieChkDB.isPresent() && !movieChkRedis.isPresent()) {
			throw new MovieNotFoundException("Redis와 DB에 존재하지 않는 영화입니다.");
		} else {
			hashOperations.delete(hashReference, movieChkRedis.get().getMovieCd());
			redisTemplateRepository.delete(movieChkDB.get());
		}
	}

	@Override
	public List<Movie> getMovieListAllWithTemp() {
		// 적당한 리스트 크기라 가정하여 Redis 에서 가져오는 것으로 구현
		Map<String, Movie> entries = hashOperations.entries(hashReference);
		List<Movie> movieList = new ArrayList<>(entries.values());
		
		return movieList;
	}

	@Override
	public Movie getMovieDetailByIdWithTemp(String movieCd) {
		// // DB 데이터 가져오기
		Optional<Movie> movieDetDB = redisTemplateRepository.findById(movieCd);
		// Redis 데이터 가져오기
		Optional<Movie> movieDetRedis = Optional.ofNullable(hashOperations.get(hashReference, movieCd));
		
		// Redis에 저장된 값이 있다면 Redis에서 가져옴
		if (movieDetRedis.isPresent()) {
			return movieDetRedis.get();
		} else {
			return movieDetDB.get();
		}
	}

}
