package com.example.tdd.ctrl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.tdd.dto.MovieDto;
import com.example.tdd.service.MovieService;

@RestController
@RequestMapping("/api/movie-api")
public class MovieController {
	
	private final MovieService movieService;
	
	// 생성자 주입 방식
	@Autowired
	public MovieController(MovieService movieService) {
		this.movieService = movieService;
	}
	
	@GetMapping("/movie/{movieCd}")
	public MovieDto getMovieDetailByCd(@PathVariable("movieCd") String movieCd) {
		return null;
	}
}
