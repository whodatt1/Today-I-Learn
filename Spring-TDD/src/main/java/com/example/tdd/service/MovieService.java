package com.example.tdd.service;

import com.example.tdd.dto.MovieDto;

public interface MovieService {
	
	MovieDto getMovieDetailByCd(String movieCd);
}
