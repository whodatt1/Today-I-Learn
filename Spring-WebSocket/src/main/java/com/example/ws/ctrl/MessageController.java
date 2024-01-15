package com.example.ws.ctrl;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.example.ws.dto.Greeting;
import com.example.ws.dto.HelloMessage;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MessageController {
	
	private final SimpMessageSendingOperations sendingOperation;
	
	// /hello api로 메시지를 보내면 /topic/greetings를 구독한 사용자들에게 브로드 캐스팅
	@MessageMapping("/chat/message")
	public Greeting greeting(HelloMessage message) throws Exception {
		Thread.sleep(1000);
		return new Greeting("안녕하세!!, " + HtmlUtils.htmlEscape(message.getName()) + ".");
	}
}
