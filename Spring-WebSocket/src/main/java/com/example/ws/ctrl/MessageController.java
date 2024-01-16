package com.example.ws.ctrl;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.stereotype.Controller;

import com.example.ws.dto.ChatMessage;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class MessageController {
	
	private final SimpMessageSendingOperations sendingOperation;
	
	@MessageMapping("/chat/message")
	public void enter(ChatMessage message) {
		if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
			message.setMessage(message.getSender() + "님이 입장했습니다.");
		}
		sendingOperation.convertAndSend("/topic/chat/room" + message.getRoomId(), message);
	}
}
