### UDP 통신이란?

-  User Datagram Protocol의 약자로 데이터를 데이터그램 단위로 처리하는 프로토콜이다.
-  비연결형 즉, 신뢰성 없는 전송 프로토콜이다.
-  데이터그램 단위로 쪼개면서 전송을 해야 하기 때문에 전송 계층이다.
-  Transport Layer 에서 사용하는 프로토콜

### TCP와 UDP의 탄생 비화

-  IP의 역할은 Host to Host(장치 to 장치)만을 지원한다. 장치에서 장치로 이동은 IP로 해결되지만, 하나의 장비안에서 수많은 프로그램들이 통신을 할 경우에는 IP만으로는 한계가 있다.
-  또한 IP에서 오류가 발생한다면 ICMP에서 알려준다. 하지만 ICMP는 알려주기만 할 뿐 대처를 못하기 때문에 IP보다 위에서 처리를 해주어야 한다.

첫번째 항목을 해결하기 위해 포트 번호가 나오게 됐고, 두번째 항목을 해결하기 위해 상위 프로토콜인 TCP와 UDP가 나오게 되었다. 

> **ICMP**
> 인터넷 제어 메시지 프로토콜로 네트워크 컴퓨터 위에서 돌아가는 운영체제에서 오류 메시지를 전송받는데 주로 쓰인다.

### TCP와 UDP가 어떻게 오류를 해결하는가

-  TCP : 데이터의 분실, 중복, 순서가 뒤바뀜 등을 자동으로 보정해줘서 송수신 데이터의 정확한 전달을 할 수 있도록 해준다.
-  UDP : IP가 제공하는 정도의 수준만을 제공하는 간단한 IP 상위 계층의 프로토콜이다. TCP와는 다르게 에러가 날 수 도 있고, 재전송이나 순서가 뒤바뀔 수도 있어서 이 경우, 어플리케이션에서 처리하는 번거로움이 존재한다.

### UDP 사용 이유?

-  UDP의 결정적인 장점은 데이터의 신속성이다. 데이터 처리가 TCP보다 빠르다.
-  주로 실시간 방송과 온라인 게임에서 사용된다. 네트워크 환경이 안 좋을 때 끊기는 현상을 생각하면 된다.

### DNS(Domain Name System)에서 UDP를 사용하는 이유

-  Request의 양이 적다 -> UDP Request에 담길 수 있다.
-  3 way handshaking으로 연결을 유지할 필요가 없다. (오버헤드 발생)
-  Request에 대한 손실은 Application Layer에서 제어가 가능하다.
-  DNS : port 53번
-  하지만 TCP를 사용할 때가 있다. 크기가 512(UDP 제한)이 넘을 때 TCP를 자용해야 한다.

### UDP Header

![](https://camo.githubusercontent.com/0392ba22518c1a2958221bbd8d42d75310f20de1fccebc7189170d3dbf149381/68747470733a2f2f74312e6461756d63646e2e6e65742f6366696c652f746973746f72792f323732413541333835373539323637423336)

-  포트 - Destination Post : 도착지 포트 - Length : 길이 - _Checksum_: 오류 검출 - 중복 검사의 한 형태로, 오류 정정을 통해 공간이나 시간 속에서 송신된 자료의 무결성을 보호하는 단순한 방법이다.
-  이렇게 간단하므로 TCP 보다 용량이 가볍고 송신 속도가 빠르게 작동된다.
-  그러나 확인 응답을 못하므로, TCP보다 신뢰도가 떨어진다.
-  UDP는 비연결성, TCP는 연결성으로 정의할 수 있다.

**DNS와 UDP 통신 프로토콜을 사용함**

DNS는 데이터를 교환하는 경우이다.

이때, TCP를 사용하게 되면, 데이터를 송신할 때 까지 세션 확립을 위한 처리를 하고, 송신한 데이터가 수신 되었는지 점검하는 과정이 필요하므로, Protocol overhead가 UDP에 비해서 크다.

---

DNS는 Application Layer Protocol이다.

모든 Application Layer Protocol은 TCP, UDP 중 하나의 Transport Layer Protocol을 사용해야 한다. (TCP는 reliable, UDP는 not reliable) / DNS는 reliable 해야할 것 같은데 왜 UDP를 사용할까?

**사용 이유**

1. TCP가 3 way handshake를 사용하는 반면, UDP는 connection을 유지 할 필요가 없다.
2. DNS Request는 UDP segment에 꼭 들어갈 정도로 작다. DNS 쿼리는 single UDP Request와 서버로부터의 single UDP Reply로 구성되어 있다.
3. UDP는 not reliable이나, reliability는 Application Layer에 추가 될 수 있다. (Timeout 추가나, resend 작업을 통해)

DNS는 UDP를 53번 port에서 사용한다.

---

그러나 TCP를 사용해야 하는 경우가 있다.

Zone Transfer를 사용해야 하는 경우에는 TCP를 사용해야 한다.

> **Zone Transfer**
> DNS 서버간의 요청을 주고 받을 때 사용하는 Transfer

만약에 데이터가 512 바이트를 넘거나, 응답을 못받는 경우 TCP를 사용한다.