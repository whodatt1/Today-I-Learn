package com.example.demo.IoC;

import org.springframework.beans.factory.annotation.Autowired;

public class AClass {
	
	/*
	 * 생성자 내부에서 직접 생성하여 BClass를 초기화
	 * 객체 생명주기나 메서드의 호출을 개발자가 직접 제어함 
	private BClass bClass;
	
	public AClass() {
		bClass = new BClass();
	}
	
	*/
	
	// BClass란 객체가 Spring Container에게 관리되고 있는 Bean ( 스프링이 관리하는 객체 )이라면 @Autowired 어노테이션을 통해 객체를 주입받는다.
	// 개발자 본인이 아닌 Spring Container에서 객체를 생성하여 해당 객체에 주입 시켜준 것이기 때문에 이것을 제어의 역전(IoC)이라고 한다.
	@Autowired
	private BClass bClass2;
}