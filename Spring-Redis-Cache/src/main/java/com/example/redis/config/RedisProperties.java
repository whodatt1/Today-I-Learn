package com.example.redis.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Getter;

@Getter
@ConfigurationProperties(prefix = "redis")
public class RedisProperties {
	
	private String host;
	private int port;
}
