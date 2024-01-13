package com.example.ws.ctrl;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import com.example.ws.dto.Greeting;
import com.example.ws.dto.HelloMessage;

@Controller
public class MessageController {
	
	// /hello api로 메시지를 보내면 /topic/greetings를 구독한 사용자들에게 브로드 캐스팅
	@MessageMapping("/hello")
	@SendTo("/topic/greetings")
	public Greeting greeting(HelloMessage message) throws Exception {
		Thread.sleep(1000);
		return new Greeting("Hello!!, " + HtmlUtils.htmlEscape(message.getName()) + ".");
	}
}
