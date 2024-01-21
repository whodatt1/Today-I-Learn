package com.example.ws.dto;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChatRoom {
	
	private String roomId;
	private String roomName;
	
	public static ChatRoom create(String roomName) {
		ChatRoom room = new ChatRoom();
		room.roomId = UUID.randomUUID().toString();
		room.roomName = roomName;
		return room;
	}
	
}
