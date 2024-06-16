package com.example.reactivetest;

import java.util.Iterator;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

// 구독 정보 (구독자, 어떤 데이터를 구독할지) 정보 보유 해야한다.
public class MySubscription implements Subscription {
	
	private Subscriber s;
	private Iterator<Integer> it;
	
	

	public MySubscription(Subscriber s, Iterable<Integer> its) {
		this.s = s;
		this.it = its.iterator();
	}

	@Override
	public void request(long n) { // 1
		while (n > 0) {
			if (it.hasNext()) {
				s.onNext(it.next()); // 1,2,3,4,5,6,7,8,9,10
			} else {
				s.onComplete();
				break;
			}
			n--;
		}
	}

	@Override
	public void cancel() {
		
	}
	
	
}
