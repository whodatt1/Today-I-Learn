package com.example.demo.DIP;

// DIP => 의존 역전 원칙
// 1. 상위 모듈은 하위 모듈에 의존해서는 안된다.
// 2. 둘다 추상화에 의존해야 한다
// DI 예제에서 Game => JRPG 에 의존하는 방향이었다면 추상화를 통해 이 방향을 역전 시킬 수 있다
// GameWithDIP => RolePlayingGame <= JRPG, WRPG, MMORPG
// 장르 변화로부터 자유로워졌다!
public class GameWithDIP {
	private RolePlayingGame rolePlayingGame;
	
	public GameWithDIP(RolePlayingGame rolePlayingGame) {
		this.rolePlayingGame = rolePlayingGame;
	}
}