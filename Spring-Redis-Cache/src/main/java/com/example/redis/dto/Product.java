package com.example.redis.dto;


import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
// 특정 해쉬값을 키, 해당 클래스의 인스턴스 값으로 적재한다
@RedisHash
public class Product {
	
	@Id
	Long productId;
	
	String productName;
	
	String delYn;
	
	// 유효시간
	@TimeToLive
	private Long ttl;
	
	private LocalDateTime regAt;
	
	private LocalDateTime modAt;
	
}
