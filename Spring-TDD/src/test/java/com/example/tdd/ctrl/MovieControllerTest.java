package com.example.tdd.ctrl;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.example.tdd.dto.Movie;
import com.example.tdd.service.MovieService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

@WebMvcTest(MovieController.class)
public class MovieControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	MovieService movieService;
	
	// http://localhost:8080/api/movie-api/movie/{movieCd}
	@Test
	@DisplayName("Get movie test")
	void getMovieTest() throws Exception {
		
		// given : Mock 객체가 특정 상황에서 해야하는 행위를 정의하는 메소드
		given(movieService.getMovieDetailByCd("A123")).willReturn(
				new Movie("A123", "Escape", 11000, 999));
		
		String movieCd = "A123";
		
		// andExpect : 기대 값이 나왔는지 체크해 보는 메서드
		mockMvc.perform(
				get("/api/movie-api/movie/" + movieCd))
		.andExpect(status().isOk())
		.andExpect(
				jsonPath("$.movieCd").exists()) // json path의 depth가 깊어지면 .을 추가하여 탐색 가능
		// ex) $.movieCd.movieNm
		.andExpect(jsonPath("$.movieNm").exists())
		.andExpect(jsonPath("$.ticketPrice").exists())
		.andExpect(jsonPath("$.seat").exists())
		.andDo(print());
		
		// verify : 해당 객체의 메소드가 실행되었는지 체크
		verify(movieService).getMovieDetailByCd(movieCd);
	}
	
	// http://localhost:8080/api/movie-api/movie
	@Test
	@DisplayName("Insert movie test")
	void insertMovieTest() throws Exception {
		
		given(movieService.insertMovie(new Movie("B123", "Passion", 13000, 999))).willReturn(
				new Movie("B123", "Passion", 13000, 999));
		
		Movie movie = Movie.builder().movieCd("B123").movieNm("Passion")
									.ticketPrice(13000).seat(999).build();
		
		Gson gson = new Gson();
		// movieDto를 JSON 형태로 변경
		String content = gson.toJson(movie);
		
		// 아래 코드로 JSON 형태 변경 작업을 대체 가능하다.
		String json = new ObjectMapper().writeValueAsString(movie);	
		
		mockMvc.perform(
				post("/api/movie-api/movie")
					.content(content)
					.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.movieCd").exists())
			.andExpect(jsonPath("$.movieNm").exists())
			.andExpect(jsonPath("$.ticketPrice").exists())
			.andExpect(jsonPath("$.seat").exists())
			.andDo(print());
		
		verify(movieService).insertMovie(movie);
	}
}
