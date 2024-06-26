package com.example.tdd.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.tdd.dto.Movie;

public interface MovieRepository extends JpaRepository<Movie, String> {

	List<Movie> findFirst5ByMovieNm(String movieNm);

	List<Movie> findTop3ByMovieNm(String movieNm);
	
	List<Movie> findByMovieNm(String movieNm);
	
	List<Movie> queryByMovieNm(String movieNm);
	
	@Query(value = "SELECT * FROM movie m WHERE m.ticket_price > 11000", nativeQuery = true)
	List<Movie> findByTicketPriceBasisNativeQuery();
	
	@Query("SELECT m FROM Movie m WHERE m.ticketPrice > :ticketPrice")
	List<Movie> findByTicketPriceWithParameter(@Param("ticketPrice") int ticketPrice);
	
	void deleteByMovieNm(String movieNm);
	
	long removeByMovieNm(String movieNm);
}
