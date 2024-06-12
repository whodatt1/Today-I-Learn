package com.example.redis.service;

import java.util.List;

import com.example.redis.dto.Movie;

public interface RedisTemplateService {
	
	Movie insertMovieWithTemp(Movie movie);
	Movie updateMovieWithTemp(Movie movie);
	void deleteMovieWithTemp(Movie movie);
	List<Movie> getMovieListAllWithTemp();
	Movie getMovieDetailByIdWithTemp(String movieCd);
}
