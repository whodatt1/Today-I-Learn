package com.example.tdd.service.Impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.tdd.dto.MovieDto;
import com.example.tdd.repo.MovieRepository;
import com.example.tdd.service.MovieService;

@Service
public class MovieServiceImpl implements MovieService {
	
	private final MovieRepository movieRepository;
	
	@Autowired
	public MovieServiceImpl(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	@Override
	public MovieDto getMovieDetailByCd(String movieCd) {
		Optional<MovieDto> movieDetail = movieRepository.findById(movieCd);
		return movieDetail.get();
	}

	@Override
	public MovieDto insertMovie(MovieDto movieDto) {
		MovieDto insertMovie = movieRepository.save(movieDto);
		return insertMovie;
	}
	
	
}
