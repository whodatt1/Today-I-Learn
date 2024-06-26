package com.example.tdd.service;

import com.example.tdd.dto.Movie;

public interface MovieService {
	
	Movie getMovieDetailByCd(String movieCd);
	Movie insertMovie(Movie movieDto);
}
