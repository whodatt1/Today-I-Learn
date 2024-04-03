package com.example.redis.dto;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Getter;

// cacheManager 사용을 위한 DTO

@Getter
@Builder
@Entity
public class User {
	
	@Id
	private String userId;
	
	private String userName;
	
	private String delYn;
	
	private LocalDateTime regAt;
	
	private LocalDateTime modAt;
}
