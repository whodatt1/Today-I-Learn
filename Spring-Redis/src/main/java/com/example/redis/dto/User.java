package com.example.redis.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;

// cacheManager 사용을 위한 DTO

@Getter
@Builder
public class User {
	
	private String userId;
	
	private String userName;
	
	private String delYn;
	
	private LocalDateTime regAt;
	
	private LocalDateTime modAt;
}
