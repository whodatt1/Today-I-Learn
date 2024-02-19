package com.example.redis.config;

import java.time.Duration;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.example.redis.dto.Product;

import lombok.RequiredArgsConstructor;

@Configuration
@EnableCaching
@RequiredArgsConstructor
@EnableRedisRepositories
public class RedisConfig {
	
	private final RedisProperties redisProperties;
	
	// 레디스 구조에 맞는 서버 정보를 추가
	@Bean
	public RedisConnectionFactory redisConnectionFactory( ) {
		return new LettuceConnectionFactory(
					new RedisStandaloneConfiguration(redisProperties.getHost(), redisProperties.getPort())
				);
	}
	
	@Bean
	public CacheManager cacheManager(RedisConnectionFactory cf) {
		
		RedisCacheConfiguration productConfig = RedisCacheConfiguration.defaultCacheConfig()
				.computePrefixWith(cacheName -> cacheName + "::") // Cache Key prefix 설정
				.disableCachingNullValues() // 캐싱할때 nuyll 값을 비허용
				.serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer())) // Key를 직렬화 할 때 사용하는 규칙
				.serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new Jackson2JsonRedisSerializer<>(Product.class))) // Value를 직렬화 할때 사용하는 규칙
				.entryTtl(Duration.ofMinutes(3L)); // 캐시 만료시간
		
		return RedisCacheManager.RedisCacheManagerBuilder
				.fromConnectionFactory(cf)
				.withCacheConfiguration("Product", productConfig)
				.build();
	}
}
