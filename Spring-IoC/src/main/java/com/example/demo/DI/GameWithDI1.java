package com.example.demo.DI;

// 의존성 주입을 통한 의존도 완화
public class GameWithDI1 {
	private JRPG jRpg;
	
	// 필요한 의존성을 모두 포함하는 생성자를 만들고 생성자를 통해 의존성을 주입
	public GameWithDI1(JRPG jRpg) {
		this.jRpg = jRpg;
	}
	
	// 의존성을 입력받는 setter 메서드를 만들고 메서드를 호출하여 의존성을 주입
	public void setJRPG(JRPG jRpg) {
		this.jRpg = jRpg;
    }
}