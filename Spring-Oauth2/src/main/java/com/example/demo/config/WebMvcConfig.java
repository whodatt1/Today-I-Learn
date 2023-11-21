package com.example.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
	
	private final long MAX_AGE_SECS = 3600;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**")
		        .allowedOrigins("*") // 외부에서 들어오는 모든 url을 허용한다.
		        .allowedMethods("GET", "POST", "PUT", "DELETE") // 허용되는 Method
		        .allowedHeaders("*") // 허용되는 Header
		        .allowCredentials(true) // 자격증명 허용
		        .maxAge(MAX_AGE_SECS); // 허용 시간
	}
}
