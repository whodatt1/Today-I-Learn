![](https://camo.githubusercontent.com/44fe40125073c05a22961a70ae08c4e0b6b2bf5bfbd11f5ab9135e2a683241a7/68747470733a2f2f73373238302e7063646e2e636f2f77702d636f6e74656e742f75706c6f6164732f323031382f30362f6f73692d6d6f64656c2d372d6c61796572732d312e706e67)

### 7 계층을 나눈 이유

통신이 일어나는 과정을 단계별로 파악하여 특정한 곳에 이상이 생기면 다른 단계의 장비 및 소프트웨어를 건들지 않고도 이상이 생긴 단계만 고칠 수 있기 때문입니다.

### 1계층 - 물리계층 ( Physical Layer )

이 계층에 속하는 대표적인 장비는 통신 **케이블**, **리피터**, **허브** 등이 있습니다.
단지 데이터를 전기적인 신호로 변환해서 주고받는 기능을 진행하는 공간이며, 전송하려는 또는 받으려는 데이터가 무엇인지, 어떤 에러가 있는지 등은 신경 쓰지 않습니다.

### 2계층 - 데이터 링크계층 ( DataLink Layer )

이 계층에 속하는 대표적인 장비는 **브릿지**, **스위치** 등이 있습니다.
물리계층을 통해서 송수신되는 정보의 오류와 흐름을 관리하여 안전한 정보의 전달을 수행할 수 있도록 도와주는 역할을 합니다. Mac 주소를 통해 통신합니다. 프레임에 Mac 주소를 부여하고 에러검출, 재전송, 흐름제어를 진행합니다.

### 3계층 - 네트워크 계층 ( Network Layer )

이 계층에 속하는 대표적인 장비는 **라우터** 이며 여기서 IP 주소를 사용합니다.
데이터를 목적지까지 가장 안전하고 빠르게 전달하는 기능을 담당합니다. 라우터를 통해 이동할 경로를 선택하여 IP 주소를 지정하고, 해당 경로에 따라 패킷을 전달해줍니다. 라우팅, 흐름 제어, 오류 제어, 세그먼테이션 등을 수행합니다.

### 4계층 - 전송 계층 ( Transport Layer )

TCP와 UDP 프로토콜을 통하여 통신을 활성화합니다. 포트를 열어두고, 프로그램들이 전송을 할 수 있도록 제공해줍니다. 만약 데이터가 왔다면 4계층에서 해당 데이터를 하나로 합쳐서 5계층에 던져줍니다. 단대단 오류제어 및 흐름제어 이 계층까지는 물리적인 계층에 속합니다.

전송 계층은 특정 특정 연결의 유효성을 제어하고, 일부 프로토콜은 상태 개념이 있고 ( stateful ), 연결 기반 ( connection oriented ) 입니다.
이는 전송 계층이 패킷들의 전송이 유효한지 확인하고 전송 실패한 패킷들을 다시 전송한다는 것을 뜻합니다.

-  TCP : 신뢰성, 연결지향적
-  UDP: 비신뢰성, 비연결성, 실시간

### 5계층 - 세션 계층 ( Session Layer )

데이터가 통신하기 위한 논리적인 연결을 말합니다. 양 끝단의 응용 프로세스가 통신을 관리하기 위한 방법을 제공합니다. 동시 송수신 방식 ( duplex ), 반이중 방식 ( half-duplex ), 전이중 방식 ( full duplex ) 의 통신과 함께, 체크 포인팅과 유휴, 종료, 다시 시작 과정등을 수행합니다. 이 계층은 TCP/IP 세션을 만들고 없애는 책임을 집니다.

### 6계층 - 표현 계층 ( Presentation Layer )

데이터 표현이 상이한 응용 프로세스의 독립성을 제공하고 암호화 합니다. 파일 인코딩 명령어를 포장, 압축, 암호화 하는 역할을 합니다. ex ) JPEG, MPEG 등

### 7계층 - 응용 계층 ( Application Layer )

최종 목적지로서 HTTP, FTP, SMTP, POP3, IMAP, Telnet 등과 같은 프로토콜이 있습니다.
사용자 인터페이스, 전자우편, 데이터베이스 관리 등의 서비스를 제공합니다.