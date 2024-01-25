## WebSocket

두 프로그램 간의 메시지를 교환하기 위한 통신 방법중 하나입니다. HTML 5에서 많이 사용됩니다.

### 특징

1.  양방향 소통

웹소켓에서는 서버와 브라우저 사이에 양방향 소통이 가능합니다. 브라우저는 서버가 직접 보내는 데이터를 원할 때에 받을 수 있고, 사용자가 다른 웹사이트로 이동하지 않아도 최신 데이터가 적용된 웹을 볼 수 있도록 해줍니다. 채팅, 실시간 주식 차트, 게임등에 효과적으로 사용할 수 있습니다.

2.  작동 원리

서버와 클라이언트 간의 웹소켓 연결이 HTTP 프로토콜을 통해 이루어집니다. 연결이 정상적으로 이루어진다면 서버와 클라이언트 간의 웹소켓 연결 (TCP/IP기반) 이 이루어지고 일정시간이 되면 HTTP 연결은 자동으로 끊어집니다. 웹소켓 API는 아주 간단한 기능들만 제공되고 있기 때문에 대부분 SockJS나 Socket.io 처럼 오픈소스 라이브러리를 많이 사용하고 있으며 메시지 포맷 또한 STOMP 같은 프로토콜을 같이 이용합니다.

3.  문제점

-  프로그램 구현에 보다 많은 복잡성을 초래합니다. 웹 소켓은 HTTP와 달리 Stateful protocol이기 때문에 서버와 클라이언트 간의 연결을 항상 유지해야 하며 만약 비정상적으로 연결이 끊어질 경우 적절하게 대응해야 합니다. 이는 기존의 HTTP 사용시와 비교했을때 코드의 복잡성을 가중시키는 요인이 될 수 있습니다.
-  서버와 클라이언트 간의 Socket 연결을 유지하는 것 자체가 비용이 듭니다. 특히나 트래픽 양이 많은 서버같은 경우에는 CPU에 큰 부담이 갈 수 있습니다.
- 오래된 버전의 웹브라우저에서는 지원하지 않습니다 (SockJS 라이브러리 같은 경우 Fallback option을 제공합니다.)

![](https://velog.velcdn.com/images/mw310/post/96370aae-8d08-4a01-a03b-a72785a78541/image.png)

## Stomp

### 특징

- Websocket 위에서 동작하는 문자 기반 메시징 프로토콜로써 클라이언트와 서버가 전송할 메시지의 유형, 형식, 내용들을 정의하는 메커니즘이다.
- TCP와 웹소켓같이 신뢰할 수 있는 양방향 스트리밍 네트워크 프로토콜에서 사용할 수 있다.
- 기본적으로 pub / sub 구조로 되어있어 메시지를 전송하고 받아 처리하는 부분이 확실히 정해져있다.
- HTTP와 마찬가지로 frame을 사용해 전송하는 프로토콜이다.

### Spring boot 에서 사용할 시 장점

메시징 프로토콜과 메시징 형식을 개발할 필요가 없다. STOMP 클라이언트는 Java 클라이언트를 포함해서 사용할 수 있다. 메시지 브로커를 사용하면 구독을 관리하고 메시지를 브로드캐스트하는데 사용할 수 있다

> BroadCast란?
> 유저가 접속 할 때 이를 모든 클라이언트에 알리고, 서버에도 로깅합니다. 이때, 모든 클라이언트에 메시지를 보내는 것을 브로드캐스트한다고 말합니다.

![](https://camo.githubusercontent.com/78d06afcaacec940b741df7d41158f547170332976c5d8f5de74d1a732c47486/68747470733a2f2f696d616765732e76656c6f672e696f2f696d616765732f7261696e626f777765622f706f73742f31356661616230362d656466312d346132312d626438652d6330383164396135656562382f696d6167652e706e67)

## 코드

### roominside.html


```html:roominside.html

<!DOCTYPE html>

<html lang="en" xmlns:th="http://www.w3.org/1999/xhtml">

<head>

<meta charset="UTF-8">

<title>WebSocket Example</title>

<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>

<script src="https://cdn.jsdelivr.net/npm/@stomp/stompjs@7.0.0/bundles/stomp.umd.min.js"></script>

<!--<link href="/src/main/resources/static/styles/main.css" rel="stylesheet">

<script src="/src/main/resources/static/js/app.js"></script>-->

</head>

<body>

<noscript>
	
	<h2 style="color: #ff0000">
	
	귀하의 브라우저가 Javascript를 지원하지 않습니다. WebSocket은 Javascript를 의존하므로 Javascript를 활성화 후 이 페이지를 새로고침 하세요!
	
	</h2>

</noscript>

<div class="container" id="app">

	<div class="col-md-12">
	
		<h3 th:text="${roomName}"></h3>
	
	</div>
	
	<div class="input-group">
	
		<div class="input-group-prepend">
		
		<label class="input-group-text">내용</label>
		
		</div>
		
		<input type="text" class="form-control" id="message" th:onkeyup="if(event.keyCode=='13'){event.preventDefault(); detailView.sendMessage();}">
		
		<div class="input-group-append">
	
			<button class="btn btn-primary" type="button" th:onclick="detailView.sendMessage()">보내기</button>
	
		</div>
	
	</div>
	
	<textarea id="chatOutput" rows="24"></textarea>

</div>

<script src="/webjars/sockjs-client/1.1.2/sockjs.min.js"></script>

<script src="/webjars/stomp-websocket/2.3.3-1/stomp.min.js"></script>

<script>

var roomId = '[[${roomId}]]';

var socket = new SockJS("/websc");

var ws = Stomp.over(socket);

var reconnect = 0;

// 대화명

var sender = localStorage.getItem('wschat.sender');

var detailView = {

init : function() {

detailView.connect();

},

connect : function() {

ws.connect({}, function(frame) {

ws.subscribe("/topic/chat/room/" + roomId, function(message) {

var recv = JSON.parse(message.body);

detailView.recvMessage(recv);

});

ws.send("/app/chat/message", {}, JSON.stringify({type:'ENTER', roomId: roomId, sender: sender, message : ''}))

}, function(error) {

if (reconnect++ <= 5) {

setTimeout(function() {

console.log("재연결중");

socket = new SockJS("/websc");

ws = Stomp.over(socket);

detailView.connect();

}, 10*1000);

}

});

},

sendMessage : function() {

var message = $("#message").val();

ws.send("/app/chat/message", {}, JSON.stringify({type:'TALK', roomId: roomId, sender: sender, message: message}))

$("#message").val("");

},

recvMessage : function(recv) {

var sender = recv.type == 'ENTER' ? '[알림]' : recv.sender;

var inputMessage = recv.message;

$("#chatOutput").append(sender + " : " + inputMessage + "\n");

}

}

$(document).ready(function() {

detailView.init();

});

</script>

</body>

</html>
```
sendMessage 안 경로인 "/app/chat/message" 요청은

```java:MessageController.java
package com.example.ws.ctrl;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.web.bind.annotation.RestController;

import com.example.ws.dto.ChatMessage;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class MessageController {
	
	private final SimpMessageSendingOperations sendingOperation;
	
	@MessageMapping("/chat/message")
	public void enter(ChatMessage message) {
		if (ChatMessage.MessageType.ENTER.equals(message.getType())) {
			message.setMessage(message.getSender() + "님이 입장했습니다.");
		}
		sendingOperation.convertAndSend("/topic/chat/room/" + message.getRoomId(), message);
	}
}

```
enter() 메서드를 호출합니다. MessageMapping의 경로가 "/chat/message" 이지만 WebSocketConfig.java 의 setApplicationDestinationPrefixes() 를 통해 prefix 가 붙어 실질적인 경로는 "/app/chat/message" 이 됩니다.

이렇게 메시지를 보내면 컨트롤러 내부에서 "/topic/chat/room/{roomId}"를 구독한 클라이언트에게 메시지를 전달하게 됩니다. roominside.html 의 ws.subscribe("/topic/chat/room/" + roomId) 로 구독 경로를 설정하고 있기때문에 메시지를 받을 수 있게됩니다. 
