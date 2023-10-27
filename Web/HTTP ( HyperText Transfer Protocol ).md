### HTTP 개요

하이퍼 텍스트 전송 프로토콜( HTTP )은 초기에는 HTML과 같은 하이퍼 미디어 문서를 주로 전송했지만, 최근에는 Plain text, JSON, XML 등 다양한 형태의 정보도 전송하는 프로토콜 입니다. HTTP는 웹에서 이루어지는 모든 데이터 교환의 기초가 되며,  HTTP는 클라이언트가 요청을 생성하기 위한 연결을 연 후 응답을 받을 때 까지 대기하는 클라이언트-서버 프로토콜이기도 합니다. HTTP는 무상태 ( Stateless ) 프로토콜이며, 이는 서버가 두 요청 간 어떠한 상태나 데이터를 유지하지 않음을 나타냅니다.  ( 상태를 유지하기 위해 Cookie와 Session을 사용합니다. ) 일반적으로 안정적인 TCP/IP 레이어를 기반으로 사용하는 응용 프로토콜이다.

> TCP/IP
> TCP/IP란 데이터가 의도된 목적지에 닿을 수 있도록 보장해주는 통신 규약입니다. TCP와 IP 두가지의 프로토콜로 구성이 됩니다.

### HTTP의 동작 방식

> **클라이언트**
> 서버에게 요청을 보내는 리소스 사용자 ex) 웹 브라우저, 모바일 애플리케이션, IoT( 사물인터넷 ) 등
> **서버**
> 클라이언트의 요청에 대한 응답을 제공하는 리소스 관리자

클라이언트 ( 웹 브라우저, 모바일 등 )가 브라우저를 통해서 어떠한 서비스를 URI를 통해 서버에 요청 ( Request ) 하면 서버에서는 해당 요청에 대한 결과를 응답 ( Response ) 하는 형태로 동작한다.

![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FdcUnst%2FbtqWXaFbg2p%2F86WwcX7OsOvnkoO71T0RbK%2Fimg.png)

### HTTP 요청 메서드

### GET

특정 리소스를 받기 ( 조회 ) 위한 요청이다. 따라서, 리소스의 생성, 수정 및 삭제 등에 사용해서는 안된다.

### POST

리소스를 생성하거나 컨트롤러를 실행하는 데 사용한다.

### PUT

변경 가능한 리소스를 업데이트 하는 데 사용되며 항상 리소스 식별 정보 ( 키값 ) 를 포함해야 한다.

### PATCH

변경 가능한 리소스의 부분 업데이트에 사용되며 항상 리소스 식별 정보 ( 키값 ) 를 포함해야 한다.
**PUT을 사용하여 전체 객체를 업데이트하는 것이 통상 사용되며 거의 사용되지 않는다.**

### DELETE

특정 리소스를 제거하는 데 사용한다.
**일반적으로 Request Body가 아닌 URI 경로에 제거하려는 리소스의 ID를 전달한다.**

### HEAD

클라이언트가 본문 없이 리소스에 대한 헤더만 검색하는 경우 사용합니다.
**일반적으로 클라이언트가 서버에 리소스가 있는지 확인하거나 메타 데이터를 읽으려는 때만 GET 대신 사용합니다.**

> 메타 데이터
> 속성 정보라고도 불리며 데이터에 관한 구조화된 데이터, 다른 데이터를 설명해주는 데이터이다.
> 대량의 정보 가운데에서 찾고있는 정보를 효율적으로 찾아내기 위해 일정한 규칙에 따라 콘텐츠에 대하여 부여되는 데이터 ex) 인스타그램의 해시태그 ( # ) 와 유사한 역할

### OPTIONS

클라이언트가 서버의 리소스에 대해 수행 가능한 동작을 알아보기 위해 사용합니다.
**일반적으로 서버는 이 리소스에 대해 사용할 수 있는 HTTP 요청 메서드를 포함하는 Allow 헤더를 반환합니다. ( CORS에 사용)**

> CORS ( Cross-Origin Resource Sharing )
> CORS는 출처가 다른 자원들을 공유한다는 뜻으로, 한 출처에 있는 자원에서 다른 출처에 있는 자원에 접근하도록 하는 개념입니다.

### HTTP 메시지


### 요청 ( Request )

![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fc7mI3U%2FbtqWX45M76d%2FgGoVLK6rcUJhekrxMcq6a1%2Fimg.png)

**첫줄**

HTTP Method, 보통 클라이언트가 수행하고자 하는 동작을 정의한 GET, POST 같은 동사나 OPTIONS, HEADER와 같은 명사입니다. Path 이 후 프로토콜의 종류와 버전이 기재됩니다.

**두번째 줄 이후**

Request Headers 부분은 리소스를 요청하는 경로 즉, 요청하고자 하는 서버의 도메인을 적습니다. 위의 예시에서 포트 번호가 생략된 것은 80번 포트 ( HTTP의 기본 포트 ) 이기 때문이다.

### 응답 ( Response )

![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FCmjnf%2FbtqWTYTN3X1%2F34p8xLsQtEIk0xMzyjIw8k%2Fimg.png)

**첫줄**

프로토콜의 종류와 버전 HTTP의 상태 코드, HTTP 상태 메시지를 적습니다.

**두번째줄**

HTTP Response의 헤더 부분으로 선택 사항으로 가져온 리소스가 포함되는 본문입니다.

### HTTP STATUS CODE

HTTP 응답 상태 코드는 HTTP 요청에 대한 성공과 실패 여부를 알려줍니다.

-  10x : 정보 확인
-  20x : 통신 성공
-  30x : 리다이렉트
-  40x : 클라이언트 오류
-  50x : 서버 오류

### 200 번 대 : 통신 성공

|상태코드|이름|의미|
|:-------|:---|:---|
|200|OK|요청 성공 ( GET )|
|201|Create|생성 성공 ( POST )|
|202|Accepted|요청 접수 O, 리소스 처리 X|
|204|No Contents|요청 성공 O, 내용 없음|

### 300 번 대 : 리다이렉트

|상태코드|이름|의미|
|:-------|:---|:---|
|300|Multiple Choice|요청 URI에 여러 리소스가 존재|
|301|Move Permanently|요청 URI가 새 위치로 옮겨감|
|304|Not Modified|요청 URI의 내용이 변경 X|

### 400 번 대 : 클라이언트 오류

|상태코드|이름|의미|
|:-------|:---|:---|
|400|Bad Request|API에서 정의되지 않은 요청 들어옴|
|401|Unauthorized|인증 오류|
|403|Forbidden|권한 밖의 접근 시도|
|404|Not Found|요청 URI에 대한 리소스 존재 X|
|405|Method Not Allowed|API에서 정의되지 않은 메소드 호출|
|406|Not Acceptable|처리 불가|
|408|Request TImeout|요청 대기 시간 초과|
|409|Conflict|모순|
|429|Too Many Request|요청 횟수 상환 초과|

### 500 번 대 : 서버 오류

|상태코드|이름|의미|
|:-------|:---|:---|
|500|Internal Server Error|서버 내부 오류|
|502|Bad Gateway|게이트웨이 오류|
|503|Service Unavailable|서비스 이용 불가|
|504|Gateway Timeout|게이트웨이 시간 초과|

### 참고한 사이트

-  https://developer.mozilla.org/ko/docs/Web/HTTP/Overview
-  https://surprisecomputer.tistory.com/54
-  https://github.com/gyoogle/tech-interview-for-developer/blob/master/Web/HTTP%20status%20code.md
-  https://developer.mozilla.org/ko/docs/Web/HTTP/Status