package com.example.demo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import com.example.demo.aop.service.CoreConcern;
import com.example.demo.aop.serviceimpl.CoreConcernImpl;

@SpringBootTest
class SpringAopApplicationTests {
	
	@Autowired
	private CoreConcern coreConcern; 
	
	@Test
	@DisplayName("Not AOP 테스트")
	void notAopTest() {
		// 부가적인 로직 시작
		StopWatch sw = new StopWatch();
		sw.start();
		// 부가적인 로직 종료
		
		// 비즈니스 로직 시작
		coreConcern.businessLogic(10000);
		// 비즈니스 로직 종료
		
		// 부가적인 로직 시작
		sw.stop();
		System.out.println("소요 시간 : " + sw.getTotalTimeSeconds());
		// 부가적인 로직 종료
	}
	
	/*
	 * AOP를 구현하기 위해 Proxy 패턴을 사용
	 * Java에는 Proxy.newProxyInstance로 프록시를 생성할 수 있습니다.
	 */
	@Test
	@DisplayName("AOP 테스트")
	void aopTest() {
		
		CoreConcern proxyCoreConcern = (CoreConcern) Proxy.newProxyInstance(
				CoreConcernImpl.class.getClassLoader(),
				new Class[]{CoreConcern.class},
				// 앞, 뒤 부가로직
				new InvocationHandler() {
					
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						
						// 부가적인 로직 시작
						StopWatch sw = new StopWatch();
						sw.start();
						// 부가적인 로직 종료
						
						int result = (int) method.invoke(coreConcern, args);
						
						// 부가적인 로직 시작
						sw.stop();
						System.out.println("소요 시간 : " + sw.getTotalTimeSeconds());
						// 부가적인 로직 종료
						
						return result;
					}
				});
		
		/*
		 * 실행
		 * 비즈니스 로직을 실행하면, 프록시 패턴에 의해 앞뒤로 부가기능이 실행됩니다.
		 * 부가기능을 제거하거나 내용을 변경하면 일괄적으로 적용됩니다.
		 */
		
		proxyCoreConcern.businessLogic(1000);
	    proxyCoreConcern.businessLogic(2000);
	    proxyCoreConcern.businessLogic(3000);
	}

}