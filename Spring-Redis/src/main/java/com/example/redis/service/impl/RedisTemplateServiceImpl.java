package com.example.redis.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.redis.dto.Movie;
import com.example.redis.service.RedisTemplateService;
import com.example.redis.util.RedisUtils;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RedisTemplateServiceImpl implements RedisTemplateService {
	
	private final RedisUtils redisUtils;

	@Override
	public Movie insertMovieWithTemp(Movie movie) {
		
		redisUtils.setData("Movie::"+ movie.getMovieCd(), movie, (long) 120000);
		
		Movie newMovie = (Movie) redisUtils.getData("Movie::"+ movie.getMovieCd());
		
		
		
		return newMovie;
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
