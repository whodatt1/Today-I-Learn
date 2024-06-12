package com.example.redis.config;


import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.RequiredArgsConstructor;

/*
 * 
 * 1. CacheManager을 이용하는 방법
 * 2. RedisTemplate을 이용하는 방법
 * 3. RedisRepository를 이용하는 방법
 * 
 */


@Configuration
@EnableCaching
@RequiredArgsConstructor
@EnableRedisRepositories
public class RedisConfig {
	
	private final RedisProperties redisProperties;
	
	// 레디스 구조에 맞는 서버 정보를 추가
	@Bean
	RedisConnectionFactory redisConnectionFactory() {
		RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration();
		redisStandaloneConfiguration.setHostName(redisProperties.getHost());
		redisStandaloneConfiguration.setPort(redisProperties.getPort());
		redisStandaloneConfiguration.setPassword(redisProperties.getPassword());
		LettuceConnectionFactory lettuceConnectionFactory = new LettuceConnectionFactory(redisStandaloneConfiguration);
		return lettuceConnectionFactory;
	}
	
	// CacheManager 사용을 위한 Bean
	// Redis Key별 Serialize 생성
	@Bean
	CacheManager cacheManager(RedisConnectionFactory cf) {
		
		RedisCacheConfiguration redisCacheConfig = RedisCacheConfiguration.defaultCacheConfig()
				.computePrefixWith(cacheName -> "prefix::" + cacheName + "::") // Cache Key prefix 설정
				.disableCachingNullValues() // 캐싱할때 null 값을 비허용
				.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer())) // Key를 직렬화 할 때 사용하는 규칙
				.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer())); // Value를 직렬화 할때 사용하는 규칙
		
		return RedisCacheManager.RedisCacheManagerBuilder
				.fromConnectionFactory(cf)
				.cacheDefaults(redisCacheConfig)
				.build();
	}
	
	// RedisTemplate 사용을 위한 Bean
	@Bean
	RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory cf) {
		RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
		redisTemplate.setConnectionFactory(cf);
		
		redisTemplate.setKeySerializer(new StringRedisSerializer());
		redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
		
		redisTemplate.setHashKeySerializer(new StringRedisSerializer());
		redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
		
		return redisTemplate;
	}
}
