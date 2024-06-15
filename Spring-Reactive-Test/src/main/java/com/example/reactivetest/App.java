package com.example.reactivetest;


// WebFlux = 단일스레드, 비동기 + Stream을 통해 백프레셔가 적용된 데이터만큼 간헐적 응답 가능 + 데이터 소비가 끝나면 응답이 종료
// SSE 적용(Servlet, Webflux 둘다 동작 가능) = 데이터 소비가 끝나도 Stream이 계속 유지된다.
public class App {

	public static void main(String[] args) {
		MyPub pub = new MyPub(); // 신문사 생성
		MySub sub = new MySub(); // 구독자 생성
		
		pub.subscribe(sub);
	}

}
