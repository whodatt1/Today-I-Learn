package com.example.redis.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
@ConfigurationProperties(prefix = "redis")
public class RedisProperties {
	
	private String host;
	private int port;
	private String password;
}
