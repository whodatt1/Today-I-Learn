package com.example.redis.service;

import java.util.HashMap;
import java.util.List;

import com.example.redis.dto.Movie;

public interface RedisTemplateService {
	
	Movie insertMovieWithTemp(Movie movie);
	Movie updateMovieWithTemp(Movie movie);
	void deleteMovieWithTemp(String movieCd);
	List<Movie> getMovieListAllWithTemp(HashMap<String, Object> params);
	Movie getMovieDetailByIdWithTemp(String movieCd);
}
