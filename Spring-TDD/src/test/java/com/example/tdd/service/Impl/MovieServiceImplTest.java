package com.example.tdd.service.Impl;

import static org.mockito.Mockito.verify;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.tdd.dto.Movie;
import com.example.tdd.repo.MovieRepository;

@ExtendWith(SpringExtension.class)
@Import({MovieServiceImpl.class})
public class MovieServiceImplTest {
	
	@MockBean
	MovieRepository movieRepository;
	
	@Autowired
	MovieServiceImpl movieServiceImpl;
	
	@Test
	public void getMovieTest() {
		
		// given
		Mockito.when(movieRepository.findById("A123"))
			   .thenReturn(Optional.ofNullable(new Movie("A123", "Escape", 11000, 999)));
		
		Movie movieDto = movieServiceImpl.getMovieDetailByCd("A123");
		
		Assertions.assertEquals(movieDto.getMovieCd(), "A123");
		Assertions.assertEquals(movieDto.getMovieNm(), "Escape");
		Assertions.assertEquals(movieDto.getTicketPrice(), 11000);
		Assertions.assertEquals(movieDto.getSeat(), 999);
		
		verify(movieRepository).findById("A123");
	}
	
	@Test
	public void insertMovieTest() {
		
		// given
		Mockito.when(movieRepository.save(new Movie("B123", "Passion", 13000, 999)))
			   .thenReturn(new Movie("B123", "Passion", 13000, 999));
		
		Movie movie = movieServiceImpl.insertMovie(new Movie("B123", "Passion", 13000, 999));
		
		Assertions.assertEquals(movie.getMovieCd(), "B123");
		Assertions.assertEquals(movie.getMovieNm(), "Passion");
		Assertions.assertEquals(movie.getTicketPrice(), 13000);
		Assertions.assertEquals(movie.getSeat(), 999);
		
		verify(movieRepository).save(new Movie("B123", "Passion", 13000, 999));
	}
}
