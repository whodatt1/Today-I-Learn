package com.example.redis.dto;


import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@Entity
public class Movie {
	
	@Id
	private String movieCd;
	
	private String movieName;
	
	private String delYn;
	
	private LocalDateTime regAt;
	
	private LocalDateTime modAt;

}
