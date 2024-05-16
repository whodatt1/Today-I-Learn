package com.example.redis.ctrl;

import java.util.HashMap;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.redis.dto.Movie;
import com.example.redis.exception.user.UserNotFoundException;
import com.example.redis.service.RedisTemplateService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RedisTemplateController {
	
	private final RedisTemplateService redisTemplateService;
	
	@GetMapping("/redistemp")
	public ModelAndView redistemp() {
		ModelAndView redistemp = new ModelAndView("RT_main");
		return redistemp;
	}
	
	@PostMapping("/redistemp/ins")
	public ResponseEntity<?> insertMovieWithTemp(@RequestBody Movie movie) {
		try {
			Movie newMovie = redisTemplateService.insertMovieWithTemp(movie);
			
			log.info("Contoller insertMovieWithTemp");
			
			return new ResponseEntity<>(newMovie, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/redistemp/upd")
	public ResponseEntity<?> updateMovieWithTemp(@RequestBody Movie movie) {
		try {
			Movie updMovie = redisTemplateService.updateMovieWithTemp(movie);
			
			log.info("Contoller updateMovieWithTemp");
			
			return new ResponseEntity<>(updMovie, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/redistemp/del")
	public ResponseEntity<?> deleteMovieWithTemp(@RequestBody Movie movie) {
		try {
			Movie delMovie = redisTemplateService.deleteMovieWithTemp(movie);
			
			log.info("Contoller deleteMovieWithTemp");
			
			return new ResponseEntity<>(delMovie, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/redistemp/all")
	public ResponseEntity<?> getMovieListAllWithTemp(@RequestParam HashMap<String, Object> params) {
		List<Movie> mList = redisTemplateService.getMovieListAllWithTemp(params);
		
		if (mList == null || mList.size() == 0) {
			return new ResponseEntity<>("조회할 영화가 없습니다!", HttpStatus.BAD_REQUEST);
		} else {
			return new ResponseEntity<>(mList, HttpStatus.OK);
		}
	}
	
	@GetMapping("/redistemp/detail/{movieCd}")
	public ResponseEntity<?> getMovieDetailByIdWithTemp(@PathVariable String movieCd) {
		try {
			Movie detMovie = redisTemplateService.getMovieDetailByIdWithTemp(movieCd);
			
			log.info("Contoller getMovieDetailByIdWithTemp");
			
			return new ResponseEntity<>(detMovie, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
}
