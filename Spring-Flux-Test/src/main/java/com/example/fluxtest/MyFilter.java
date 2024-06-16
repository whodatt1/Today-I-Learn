package com.example.fluxtest;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;

public class MyFilter implements Filter {
	
	private EventNotify eventNotify;
	
	public MyFilter(EventNotify eventNotify) {
		this.eventNotify = eventNotify;
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("필터 실행.");
		
		// ServletResponse를 HttpServletResponse로 다운캐스팅
		HttpServletResponse servletResponse = (HttpServletResponse) response;
		// MIME Type 설정
		//servletResponse.setContentType("text/plain; charset=utf-8"); // text/plain의 경우 PrintWriter를 flush 하여도 쌓아두다가 한번에 전송
		servletResponse.setContentType("text/event-stream; charset=utf-8"); // flush 할때마다 읽어서 화면에 전송
		
		// Stream을 열어놓고 응답
		// 1. WebFlux에선 비동기 단일 쓰레드로 동작
		// 2. MVC에서는 사용자가 들어올때마다 쓰레드가 만들어지며 동작
		// Reactive Stream 라이브러리를 쓰면 표준을 지켜서 응답 가능하다.
		// 소비가 끝나면 종료된다.
		PrintWriter out = servletResponse.getWriter();
		for (int i = 0; i < 5; i++) {
			out.print("응답 : " + i + "\n");
			out.flush();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// SSE Emitter 라이브러리를 사용하면 편하게 사용 가능하다.
		// 소비가 끝나도 종료되지 않는다.
		while (true) {
			try {
				if (eventNotify.getChange()) {
					int lastIndex = eventNotify.getEvents().size() - 1;
					out.print("응답 : " + eventNotify.getEvents().get(lastIndex) + "\n");
					out.flush();
					eventNotify.setChange(false);
				}
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		// WebFlux -> Reactive Streams 가 적용된 stream (비동기 단일스레드 동작) => 여기서 사용하는 것이 효과적
		// Servlet MVC -> Reactive Streams 가 적용된 stream (멀티 스레드 동작)
	}
}
