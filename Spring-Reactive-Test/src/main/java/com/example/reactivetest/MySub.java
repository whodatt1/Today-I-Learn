package com.example.reactivetest;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

public class MySub implements Subscriber<Integer> {
	
	private Subscription s;
	private int bufferSize = 2;

	@Override
	public void onSubscribe(Subscription s) {
		System.out.println("구독 정보 수신 완료 (구독자)");
		this.s = s;
		System.out.println("신문 한 개씩 요청 (구독자)");
		s.request(bufferSize); // 신문은 한개씩 매일 받는다 (백프레셔) 소비자가 한번에 처리할 수 있는 개수를 요청
		// request는 한번에 받을 개수 !
	}
	
	// request를 2번씩 호출하면 데이터가 꼬인다.
	// bufferSize를 설정하여 설정된 리퀘스트 횟수가 모두 소비 된 후에 리퀘스트를 재요청하도록하여 해결
	// 2번 실행
	@Override
	public void onNext(Integer t) {
		System.out.println("onNext() : " + t);
		bufferSize--;
		if (bufferSize == 0) {
			System.out.println("하루 경과");
			bufferSize = 2;
			s.request(bufferSize);
		}
	}

	@Override
	public void onError(Throwable t) {
		System.out.println("구독 중 오류 발생!");
	}

	@Override
	public void onComplete() {
		System.out.println("구독 완료");
	}
	
	
}
