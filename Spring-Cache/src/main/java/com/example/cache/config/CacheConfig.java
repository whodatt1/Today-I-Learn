package com.example.cache.config;

import java.util.List;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 캐시 활성화
@EnableCaching
@Configuration
public class CacheConfig {
	
	@Bean
	public CacheManager cacheManager() {
		// 메모리 관리 객체 (메모리 상에 캐시 저장)
		ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager();
		// 캐시 관리자가 모든 캐시에 대해 null 값을 허용하고 변환할지 여부 지정
		cacheManager.setAllowNullValues(false);
		cacheManager.setCacheNames(List.of("users"));
		return cacheManager;
	}
	
}
