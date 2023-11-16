package com.example.demo.DI;

// DI (의존성 주입) 를 적용하지 않은 예시
public class GameWithoutDI {
	private JRPG jRpg;
	
	// 게임은 JRPG에 의존하기 때문에 JRPG 클래스의 변경이 생긴다면 GameWithoutDI 클래스도 변경되어야 한다.
	public GameWithoutDI() {
		this.jRpg = new JRPG();
	}
}