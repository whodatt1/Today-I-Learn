package com.example.redis.dto;


import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Movie {
	
	private String movieCd;
	
	private String movieName;
	
	private String delYn;
	
	private LocalDateTime regAt;
	
	private LocalDateTime modAt;
}
