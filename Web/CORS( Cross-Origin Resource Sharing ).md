> CORS는 Cross-Origin Resource Sharing의 줄임말로, 교차 리소스 공유를 의미하며, 교차 출처는 '다른 출처' 라고 생각하면 이해하기 쉽습니다. 즉, 다른 출처 간의 자원을 공유하는 정책으로 생각하면 됩니다.

![](https://velog.velcdn.com/images/sun1203/post/5807870f-81b4-4b62-84bc-0be2e39ca79a/image.png)

> Protocol, Host, Path, Query String, Fragment 구성 요소 중 출처 ( Origin ) 는 Protocol과 Host 그리고 :80 :443 같은 포트 번호까지 모두 합친 것을 의미합니다.

### SOP ( Same-Origin Policy )

> CORS에 대한 개념을 이해하기 전 SOP이 무엇인지 알아야 합니다.
> SOP란 같은 Origin에만 요청을 보낼 수 있게 제한하는 보안 정책을 의미합니다.

-  Origin 구성 요소
	-  URI Schema ( ex http, https )
	-  Hostname ( ex localhost, naver.com )
	-  Port ( ex 80, 8080 )

이 중 하나라도 구성이 다르면 SOP 정책에 걸리기 때문에 ajax 요청을 보낼 수 없습니다.

http://www.example.com/dir/page.html 에 요청을 보낼 때의 예시

|비교 URL|결과|이유|
|----------|-----|-----|
|http://www.example.com/dir/page2.html|통과|같은 스키마, 호스트, 포트|
|http://www.example.com/dir2/other.html|통과|같은 스키마, 호스트, 포트|
|http://username:password@www.example.com/dir2/other.html|통과|같은 스키마, 호스트, 포트|
|http://www.example.com:81/dir/other.html|실패|다른 포트|
|https://www.example.com/dir/other.html|실패|다른 스키마|
|http://en.example.com/dir/other.html|실패|다른 호스트|
|http://example.com/dir/other.html|실패|다른 호스트|
|http://v2.www.example.com/dir/other.html|실패|다른 호스트|
|http://www.example.com:80/dir/other.html|보류|명시적인 포트, 브라우저마다 다름|

### CORS의 동작 과정

-  기본적인 흐름

	기본적으로 웹 클라이언트 애플리케이션이 다른 출처의 리소스를 요청할 때는 HTTP 프로토콜을 사용하여 요청을 보내게 되고 이때 브라우저는 요청 헤더에 Origin 이라는 필드에 요청을 보내는 출처를 함께 담습니다.

	Origin : http://google.com 과 같이 출처를 담아서 서버로 보내면, 서버는 응답 헤더의 Access-Control-Allow-Origin이라는 값에 이 리소스를 접근하는 것이 허용된 출처 목록을 담아줍니다. 이후 응답을 받은 브라우저는 자신이 보냈던 요청의 Origin 과 서버가 보내준 응답의 Access-Control-Allow-Origin을 비교합니다. 만약 허용되지 않는 Origin이면 CORS 정책 위반 이슈가 발생하며, 여기서 주의할 점은 서버의 응답은 200번이 온다는 것입니다.

### Preflight Request

> Preflight Request 방식은 일반적으로 사용하는 방식으로 브라우저는 요청을 한번에 보내지 않고 예비 요청과 본 요청으로 나누어서 서버로 전송합니다. 이때 예비 요청은 OPTIONS 메소드를 사용하여 본 요청을 보내기 전에 브라우저 스스로 이 요청이 안전한지 확인하는 역할을 합니다.

![](https://velog.velcdn.com/images/sun1203/post/0806780a-6ecc-4dd8-b0d3-0c0acc1206b4/image.png)

-  Javascript의 fetch() API를 사용하여 브라우저에게 서버의 리소스를 받아오라는 명령을 내립니다.
-  브라우저는 서버에게 예비 요청을 보냅니다.
-  예비 요청의 응답으로 Access-Control-Allow-Origin의 값을 확인하여, 허용하는 출처를 조회합니다.
	-  브라우저 요청의 출처가 허용되지 않는다면 CORS 정책 위반 이슈가 발생한다 ( 이때 응답의 상태코드는 200번이 나옵니다. )
	-  예비 요청의 응답이 성공하지 못한다면 CORS 정책 위반 이슈가 발생합니다.
-  예비 요청이 성공하면, 실제 본 요청을 보내서 서버의 리소스를 받아옵니다.

### 참고한 사이트

-  https://velog.io/@sun1203/CORS%EB%9E%80