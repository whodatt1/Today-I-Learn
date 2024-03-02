package com.example.redis.dto;


import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@RedisHash(value = "product", timeToLive = 3600) // Redis Repo 사용을 위한 어노테이션 설정
public class Product {
	
	@Id
	String productCd;
	
	String productName;
	
	String delYn;
	
	@Indexed // 필드 값으로 데이터를 찾을 수 있도록 함
	private LocalDateTime regAt;
	
	@Indexed
	private LocalDateTime modAt;
	
}
