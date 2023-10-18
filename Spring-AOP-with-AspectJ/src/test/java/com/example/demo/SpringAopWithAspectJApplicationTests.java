package com.example.demo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.aopwithaspectj.service.CalcService;

@SpringBootTest
class SpringAopWithAspectJApplicationTests {
	
	@Autowired
	private CalcService calcService;

	@Test
	@DisplayName("AOP - Around 테스트")
	void aopAroundTest() {
		calcService.sum(3, 5);
	}
	
	@Test
	@DisplayName("AOP - Before 테스트")
	void aopBeforeTest() {
		calcService.subtract(3, 5);
	}
	
	@Test
	@DisplayName("AOP - After 테스트")
	void aopAfterTest() {
	    calcService.multiply(3, 10);
	}
	
	@Test
	@DisplayName("AOP - AfterReturning 테스트")
	void aopAfterReturningTest() {
	    calcService.sum(10, 2);
	}
	
	@Test
	@DisplayName("AOP - AfterReturning 테스트")
	void aopAfterThrowingTest() {
	    calcService.divide(10, 0);
	}
}
