package com.example.demo.SpringIoC;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GameService {
	// @Component 클래스에 대해서 스프링이 해 주는 일
	// 1. Spring 서버가 뜰 때 GameService 객체 생성
	// GameService gameService = new GameService();
	
	// 2. 스프링 IoC 컨테이너에 빈 ( GameService ) 저장
	// gameServce => 스프링 IoC 컨테이너
	
	// @Controller, @Service, @Repository 어노테이션은 @Component 어노테이션을 포함
	// @Component 어노테이션은 @ComponentScan에 설정해 준 packages 위치와 하위 packages에만 적용된다
	// 현재 @ComponentScan을 적용해준 적이 없지만
	// SpringIoCApplication.java의 @SpringBootAplication에 의해 default 설정이 되어있다.
	@Autowired
	private GameRepository gameRepository;
	
	/*
	 * setter 주입
	 * 
	 @Autowired
	 public void setGameRepository(GameRepository gameRepository) {
	 	this.gameRepository = gameRepository;
	 }
	 */
	
	/*
	 * 생성자 주입
	 * 객체의 최초 생성 시점에 Spring이 의존성을 주입해 준다.
	 *  
	 @Autowired
	 public void setGameRepository(GameRepository gameRepository) {
	 	this.gameRepository = gameRepository;
	 }
	 */
	
	/*
	 * 빈을 수동으로 가져오는 방법
	 *  
	 @Autowired
     public GameService(ApplicationContext context) {
        // 빈 이름으로 가져오기
        GameRepository gameRepository = (GameRepository) context.getBean("gameRepository");
        // 빈 클래스 형식으로 가져오기
        // GameRepository gameRepository = context.getBean(GameRepository.class);
        this.gameRepository = gameRepository;
     }
	 */
}