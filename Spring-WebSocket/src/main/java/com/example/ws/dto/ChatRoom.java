package com.example.ws.dto;

import java.util.UUID;

import lombok.Data;

@Data
public class ChatRoom {
	
	private String roomId;
	private String roomName;
	
	public static ChatRoom create(String name) {
		ChatRoom room = new ChatRoom();
		room.roomId = UUID.randomUUID().toString();
		room.roomName = name;
		return room;
	}
}
