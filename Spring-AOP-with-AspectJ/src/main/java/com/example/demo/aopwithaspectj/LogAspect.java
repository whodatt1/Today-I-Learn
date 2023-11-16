package com.example.demo.aopwithaspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

/*
 * 아래 기반으로 SpringAopApplicationTests 에서 AOP 테스트 코드를 작성하였습니다. 
 */
  
@Aspect
@Component
public class LogAspect { // Aspect : 부가 기능 구현체들을 포함하고 있는 모듈
	
	// 모듈 : 외부에서 재사용 할 수 있는 패키지들을 묶은 것. 어떻게 보면 이클립스에서 개발하는 프로젝트는 하나의 모듈을 개발하는 것
	
	private final Logger log = LoggerFactory.getLogger(this.getClass());
	
	// PointCut : 적용할 지점 혹은 범위 선택
	@Pointcut("execution(public * com.example.demo.aopwithaspectj.service..*(..))")
	private void publicTarget() { };
	
	// Advice : 실제 부가기능 구현부
	@Around("publicTarget()")
	public Object calcPerformAdvice(ProceedingJoinPoint pjp) throws Throwable {
		
		log.info("성능 측정 시작.");
		StopWatch sw = new StopWatch();
		sw.start();
		
		// 비즈니스 로직
		Object result = pjp.proceed();
		
		sw.stop();
		log.info("성능 측정 종료.");
		log.info("걸린 시간 : {} ms", sw.getLastTaskTimeMillis());
		
		return result;
	}
	
	@Pointcut("execution(public * com.example.demo.aopwithaspectj.service.CalcService.subtract(..))")
	private void subtractTarget() { };
	
	@Before("subtractTarget()")
	public void subtractBeforeAdvice() {
		log.info("兮 연산 수행.");
	}
	
	@Pointcut("execution(public * com.example.demo.aopwithaspectj.service.CalcService.multiply(..))")
	private void multiplyTarget() { };
	
	@After("multiplyTarget()")
	public void multiplyAfterAdvice() {
		log.info("곱하기 연산 끝");
	}
	
	@Pointcut("execution(public * com.example.demo.aopwithaspectj.service.CalcService.sum(..))")
    private void sumTarget() { }

    @AfterReturning(value = "sumTarget()", returning = "returnValue")
    public void sumAfterReturningAdvice(Object returnValue) {
        log.info("연산 값은 {} 입니다.", returnValue);
    }
    
    @Pointcut("execution(public * com.example.demo.aopwithaspectj.service.CalcService.divide(..))")
    private void divideTarget() { }

    @AfterThrowing(value = "divideTarget()", throwing = "exception")
    public void sumAfterReturningAdvice(Exception exception) {
        log.info("나누기 연산 도중 ERROR({}) 발생", exception.getMessage());
    }
 }