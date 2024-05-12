package com.example.redis.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

// cacheManager 사용을 위한 DTO

@Getter
@Entity
@NoArgsConstructor
public class User extends BaseEntity {
	
	@Id
	private String userId;
	
	private String userName;
	
	private String delYn;
	
}
