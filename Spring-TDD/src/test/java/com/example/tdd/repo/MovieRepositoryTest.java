package com.example.tdd.repo;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.tdd.dto.Movie;

import jakarta.transaction.Transactional;

@SpringBootTest
public class MovieRepositoryTest {
	
	@Autowired
	MovieRepository movieRepository;
	
	private Movie getMovieDto(String movieCd, String movieNm, int ticketPrice, int seat) {
		return new Movie(movieCd, movieNm, ticketPrice, seat);
	}
	
	@Test
	@Transactional
	void deleteTest() {
		System.out.println("before : " + movieRepository.count());
		
		movieRepository.deleteByMovieNm("Venom2");
		movieRepository.removeByMovieNm("Venom1");
		
		System.out.println("after : " + movieRepository.count());
	}
	
	@Test
	void findTest() {
		List<Movie> findAll = movieRepository.findAll();
		System.out.println("=== TEST DATA ===");
		
		for (Movie movie : findAll) {
			System.out.println(movie.toString());
		}
		
		List<Movie> foundEntities = movieRepository.findByMovieNm("Venom4");
		System.out.println("=== TEST DATA 2 ===");
		
		for (Movie movie : foundEntities) {
			System.out.println(movie.toString());
		}
		
		List<Movie> queryEntities = movieRepository.queryByMovieNm("Venom3");
		System.out.println("=== TEST DATA 3 ===");
		
		for (Movie movie : queryEntities) {
			System.out.println(movie.toString());
		}
		
		
	}
	
	@Test
	void topTest() {
		movieRepository.save(getMovieDto("A1231", "Venom1", 11000, 999));
		movieRepository.save(getMovieDto("A1242", "Venom2", 11000, 999));
		movieRepository.save(getMovieDto("A1253", "Venom3", 11000, 999));
		movieRepository.save(getMovieDto("A1264", "Venom4", 11000, 999));
		movieRepository.save(getMovieDto("A1275", "Venom5", 12000, 999));
		movieRepository.save(getMovieDto("A1286", "Venom6", 12000, 999));
		movieRepository.save(getMovieDto("A1297", "Venom7", 12000, 999));
		movieRepository.save(getMovieDto("A1308", "Venom8", 13000, 999));
		movieRepository.save(getMovieDto("A1319", "Venom9", 13000, 999));
		
		List<Movie> foundMovies = movieRepository.findFirst5ByMovieNm("Venom");
		for (Movie movie : foundMovies) {
			System.out.println(movie.toString());
		}
		
		List<Movie> foundMovies2 = movieRepository.findTop3ByMovieNm("Venom");
		for (Movie movie : foundMovies2) {
			System.out.println(movie.toString());
		}
		
	}
	
	@Test
	void nativeQueryTest() {
		
		List<Movie> findAll = movieRepository.findAll();
		System.out.println("=== TEST DATA ===");
		
		for (Movie movie : findAll) {
			System.out.println(movie.toString());
		}
		
		List<Movie> foundMovies = movieRepository.findByTicketPriceBasisNativeQuery();
		System.out.println("=== TEST DATA 2 ===");
		
		for (Movie movie : foundMovies) {
			System.out.println(movie.toString());
		}
	}
	
	@Test
	void parameterQueryTest() {
		
		List<Movie> findAll = movieRepository.findAll();
		System.out.println("=== Param TEST DATA ===");
		
		for (Movie movie : findAll) {
			System.out.println(movie.toString());
		}
		
		List<Movie> foundMovies = movieRepository.findByTicketPriceWithParameter(12000);
		System.out.println("=== TEST DATA 2 ===");
		
		for (Movie movie : foundMovies) {
			System.out.println(movie.toString());
		}
	}
	
}
