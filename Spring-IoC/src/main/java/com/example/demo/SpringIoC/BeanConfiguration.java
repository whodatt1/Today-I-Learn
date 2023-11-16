package com.example.demo.SpringIoC;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
	// 직접 객체를 생성하여 빈으로 등록할 수 있다.
	// 설정 클래스에 @Configuration 어노테이션을 설정하고, 해당 메서드에 @Bean 어노테이션을 붙인다.
	// 그럼 Spring 서버가 뜰 때 @Bean으로 설정된 함수를 호출하고 Spring IoC에 Bean으로 저장된다.
	@Bean
	public GameRepository gameRepository() {
		String dbUrl = "";
		String username = "";
		String password = "";
		return new GameRepository(dbUrl, username, password);
	}
	
	/*
	 1. @Bean 설정된 함수 호출
	 GameRepository gameRepository = beanConfiguration.gameRepository();
	 
	 2. 스프링 IoC 컨테이너에 빈 (gameRepository) 저장
	 gameRepository => 스프링 IoC 컨테이너
	  
	 */
 }