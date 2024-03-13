package com.example.redis.ctrl;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.redis.dto.Movie;
import com.example.redis.service.RedisTemplateService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/redistemp")
@RestController
@RequiredArgsConstructor
public class RedisTemplateController {
	
	private final RedisTemplateService redisTemplateService;
	
	@PostMapping("/ins")
	public Movie insertMovieWithTemp(Movie movie) {
		Movie newMovie = redisTemplateService.insertMovieWithTemp(movie);
		return newMovie;
	}
	
	@PutMapping("/upd")
	public Movie updateMovieWithTemp(Movie movie) {
		Movie updMovie = redisTemplateService.updateMovieWithTemp(movie);
		return updMovie;
	}
	
	@DeleteMapping("/del/{movieCd}")
	public void deleteMovieWithTemp(@PathVariable String movieCd) {
		redisTemplateService.deleteMovieWithTemp(movieCd);
	}
	
	@GetMapping("/all")
	public List<Movie> getMovieListAllWithTemp() {
		List<Movie> mList = redisTemplateService.getMovieListAllWithTemp();
		return mList;
	}
	
	@GetMapping("/detail/{movieCd}")
	public Movie getMovieDetailByIdWithTemp(@PathVariable String movieCd) {
		Movie detMovie = redisTemplateService.getMovieDetailByIdWithTemp(movieCd);
		return detMovie;
	}
}
