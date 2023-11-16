package com.example.demo.aop.serviceimpl;

import org.springframework.stereotype.Service;

import com.example.demo.aop.service.CoreConcern;

/*
 * AOP => Aspect Oriented Programming 번역하면 관점지향 프로그래밍이다.
 * 
 * AOP는 주 비즈니스 로직 앞, 뒤로 부가적인 기능을 추가하고 싶을 때 사용한다.
 * 예를들어 로그처리, 보안처리, DB 트랜잭션이 있다.
 * 관점을 횡단으로 바꿔서 바라보는 것을 횡단 관심사라고 하며 부가적인 로직을 Cross Cutting Concern, 비즈니스 로직을 Core Concern 이라 한다.
 * 
 * 코드의 중복을 줄일 수 있고 주 업무 로직과 부가적인 로직을 분리하기 위해 쓴다.
 * AOP는 디자인 패턴 중 프록시 패턴 (Proxy Pattern) 을 이용하여 구현할 수 있습니다.
 * 
 * - 스프링에서는 어노테이션으로 더 쉽게 구현 가능합니다.
 * - 이 비즈니스 로직을 기반으로 SpringAopApplicationTests 에서 AOP 테스트 코드를 작성하였습니다. 
 */ 

@Service
public class CoreConcernImpl implements CoreConcern {

	@Override
	public int businessLogic(int totalCount) {
		System.out.println("����Ͻ� ���� ����");
		
		int count = 0;
		for (int i = 0; i < totalCount; i++) {
			count++;
		}
		
		return count;
	}
	
}
