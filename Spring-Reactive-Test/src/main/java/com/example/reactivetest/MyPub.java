package com.example.reactivetest;

import java.util.Arrays;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

public class MyPub implements Publisher<Integer> {
	
	Iterable<Integer> its = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

	@Override
	public void subscribe(Subscriber<? super Integer> s) {
		System.out.println("신문사 구독 요청 (구독자)");
		System.out.println("구독 정보(Subscription) 생성 중 (신문사)");
		MySubscription subscription = new MySubscription(s, its); // 구독자와 데이터 정보 넘겨준다.
		System.out.println("구독 정보 생성 완료 (신문사)");
		s.onSubscribe(subscription);
	}
	
}
