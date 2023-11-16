package com.example.demo.DI;

// 의존성 주입을 통한 의존도 완화
public class GameWithDI2 implements GenreInjection {
	private JRPG jRpg;
	
	// 의존성 주입하는 메서드를 포함하는 인터페이스를 작성 후 인터페이스를 구현하도록 하여 실행시해 이를 통해 의존성을 주입
	// setter와는 다르게 메서드 구현을 강제한다 ( 인터페이스의 특성 )
	@Override
	public void injection(JRPG jRpg) {
		this.jRpg = jRpg;
	}
}

interface GenreInjection {
	void injection(JRPG jRpg);
}