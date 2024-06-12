package com.example.redis.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.example.redis.dto.Movie;
import com.example.redis.exception.movie.MovieExistsException;
import com.example.redis.exception.movie.MovieNotFoundException;
import com.example.redis.repo.RedisTemplateRepository;
import com.example.redis.service.RedisTemplateService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RedisTemplateServiceImpl implements RedisTemplateService {
	
	private final String hashReference = "Movie";
	
	private final RedisTemplateRepository redisTemplateRepository;
	
	private final RedisTemplate<String, Movie> redisTemplate;
	
	
	@Override
	public Movie insertMovieWithTemp(Movie movie) throws MovieExistsException {
		
		HashOperations<String, String, Movie> hashOperations = redisTemplate.opsForHash();
		
		// DB 데이터 체크
		Optional<Movie> movieChkDB = redisTemplateRepository.findById(movie.getMovieCd());
		// Redis 데이터 체크
		Optional<Movie> movieChkRedis = Optional.ofNullable(hashOperations.get("Movie", movie.getMovieCd()));
		
		if (movieChkDB.isPresent() && movieChkRedis.isPresent()) {
			throw new MovieExistsException("Redis와 DB에 이미 존재하는 영화입니다.");
		} else {
			Movie jpaMovie = redisTemplateRepository.save(movie);
			
			hashOperations.putIfAbsent(hashReference, movie.getMovieCd(), jpaMovie);
			
			return jpaMovie;
		}
	}

	@Override
	public Movie updateMovieWithTemp(Movie movie) throws MovieNotFoundException {
		
		HashOperations<String, String, Movie> hashOperations = redisTemplate.opsForHash();
		// DB 데이터 체크
		Optional<Movie> movieChkDB = redisTemplateRepository.findById(movie.getMovieCd());
		// Redis 데이터 체크
		Optional<Movie> movieChkRedis = Optional.ofNullable(hashOperations.get("Movie", movie.getMovieCd()));
		
		movieChkDB.get().setMovieName(movie.getMovieName());
		
		if (!movieChkDB.isPresent() && !movieChkRedis.isPresent()) {
			throw new MovieNotFoundException("Redis와 DB에 존재하지 않는 영화입니다.");
		} else {
			Movie jpaMovie = redisTemplateRepository.save(movieChkDB.get());
			
			hashOperations.put(hashReference, movie.getMovieCd(), jpaMovie);
			
			return jpaMovie;
		}
	}

	@Override
	public void deleteMovieWithTemp(Movie movie) throws MovieNotFoundException {
		
		HashOperations<String, String, Movie> hashOperations = redisTemplate.opsForHash();
		// DB 데이터 체크
		Optional<Movie> movieChkDB = redisTemplateRepository.findById(movie.getMovieCd());
		// Redis 데이터 체크
		Optional<Movie> movieChkRedis = Optional.ofNullable(hashOperations.get(hashReference, movie.getMovieCd()));
		
		if (!movieChkDB.isPresent() && !movieChkRedis.isPresent()) {
			throw new MovieNotFoundException("Redis와 DB에 존재하지 않는 영화입니다.");
		} else {
			redisTemplateRepository.deleteById(movie.getMovieCd());
			
			hashOperations.delete(hashReference, movie.getMovieCd());
		}
	}

	@Override
	public List<Movie> getMovieListAllWithTemp() {
		
		// 이부분도 그냥 Cache에 있으면 캐시 데이터 가져오고 없으면 DB 데이터 가져오도록..
		
		HashOperations<String, String, Movie> hashOperations = redisTemplate.opsForHash();
		Map<String, Movie> entries = hashOperations.entries(hashReference);
		List<Movie> movieList = entries.values()
									   .stream()
									   .collect(Collectors.toList());
		
		if (movieList.size() > 0) {
			
			log.info("List from Redis");
			
			return movieList;
		} else {
			
			log.info("List from DB");
			
			Iterable<Movie> all = redisTemplateRepository.findAll();
			
			List<Movie> mList = new ArrayList<>();
			
			all.forEach(mList::add);
			
			return mList;
		}
		
	}

	@Override
	public Movie getMovieDetailByIdWithTemp(String movieCd) throws MovieNotFoundException {
		
		HashOperations<String, String, Movie> hashOperations = redisTemplate.opsForHash();
		// Redis 데이터 가져오기
		Optional<Movie> movieDetRedis = Optional.ofNullable(hashOperations.get(hashReference, movieCd));
		
		// Redis에 저장된 값이 있다면 Redis에서 가져옴
		if (movieDetRedis.isPresent()) {
			log.info("Detail from Redis");
			
			return movieDetRedis.get();
		} else {
			log.info("Detail from DB");
			// DB 데이터 가져오기
			Optional<Movie> movieDetDB = redisTemplateRepository.findById(movieCd);
			
			if (!movieDetDB.isPresent()) {
				throw new MovieNotFoundException("DB에 존재하지 않는 영화입니다.");
			}
			
			return movieDetDB.get();
		}
	}

}
