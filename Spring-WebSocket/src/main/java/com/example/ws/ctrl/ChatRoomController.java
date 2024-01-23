package com.example.ws.ctrl;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.ws.dto.ChatRoom;
import com.example.ws.service.ChatService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/chat")
public class ChatRoomController {
	
	private final ChatService chatService;
	
	@GetMapping("/room")
	public String rooms(Model model) {
		return "main";
	}
	
	// 모든 채팅방 목록 조회
	@GetMapping("/roomlist")
	@ResponseBody
	public List<ChatRoom> roomList() {
		return chatService.findAllRoom();
	}
	
	// 채팅방 생성
	@PostMapping("/room")
	@ResponseBody
	public ChatRoom createRoom(@RequestBody ChatRoom params) {
		return chatService.createRoom(params.getRoomName());
	}
	
	// 채팅방 입장
	@GetMapping("/room/enter/{roomId}")
	public String roomEnter(Model model, @PathVariable String roomId) {
		ChatRoom chatRoom = chatService.findById(roomId);
		model.addAttribute("roomId", roomId);
		model.addAttribute("roomName", chatRoom.getRoomName());
		return "roominside";
	}
	
	@GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId) {
        return chatService.findById(roomId);
    }
}
