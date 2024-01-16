package com.example.ws.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

	@Override
	public void registerStompEndpoints(StompEndpointRegistry registry) {
		// 연결 경로 설정. SockJS 사용하여 브라우저 등에서 웹소켓을 지원하지 않을 경우 대안 기술 사용할 수 있도록 설정
		registry.addEndpoint("/websc").setAllowedOriginPatterns("*").withSockJS(); 
	}

	@Override
	public void configureMessageBroker(MessageBrokerRegistry registry) {
		// 메모리 기반 메세지 브로커가 클라이언트에게 메세지를 브로드 캐스팅
		registry.enableSimpleBroker("/topic");
		// 서버에서 클라이언트로부터 메시지를 받을 api의 prefix 설정
		registry.setApplicationDestinationPrefixes("/app");
	}

	@Override
	public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
		// 메시지 최대크기 구성
		registry.setMessageSizeLimit(4 * 8192);
		// 클라이언트가 최초의 하위 프로토콜 메세지를 30초 안에 보내지 않으면 해당 연결이 문제가 있다 판단하여 연결 종료
		registry.setTimeToFirstMessage(30000);
	}
	
}
