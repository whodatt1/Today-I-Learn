### IoC (Inversion of Control)

객체를 개발자가 직접 생성하여 관리하는 것이 아니라 프레임워크 내부에서 필요한 객체를 스캔한 후 생성하여 heap 메모리`(스프링 컨테이너)`에 띄우고 관리한다. 해당 객체는 싱글톤으로 관리가 되며, 이것을 제어의 역전이라고 한다.

### DI (Dependency Injection)

스프링이 관리하는 객체를 내가 원하는 모든 클래스의 메소드에 가져와 사용해 줄 수 있게 한다. 싱글톤으로 관리되기 때문에 어느 객체에서 의존성 주입을 하던지 같은 주소 값을 가진 동일한 객체를 사용하게 된다.

### Filter

들어온 요청이 인증 인가 과정을 거치도록 하는 구간이다. 스프링이 기본적으로 가지고 있는 필터 기능이 존재하는데, web.xml 과 Interceptor가 있다.

### Reflection & Compile Checking

어노테이션은 컴파일러에게 해당 코드를 해석하기 위한 힌트를 제공한다.

리플렉션은 프로젝트가 실행되어 스프링 컨테이너가 생성되어 `@Component` 어노테이션이 달린 모든 것들을 스캔하는 과정에서 각 클래스가 어떤 필드, 메서드, 어노테이션을 가지고 있는지 분석하는 기법이다. (런타임 시 분석을 한다. )

컴파일 체킹은 리플렉션 과정 중 해당 분석이 유효한지 검사하는 것을 의미한다.

### MessageConverter

MessageConverter는 자바 프로그램과 다른 프로그램 사이의 언어의 격차를 줄이고자 응답과 요청을 하는 과정에서 JSON 형식으로 변환하여 주고 받을 수 있게 해준다.

### BufferedReader & BufferedWriter

스프링은 BufferedReader와 BufferedWriter를 쉽게 사용할 수 있다.

통신 단위는 Byte인데 한 영어 문자를 저장하는 데에는 1Byte면 충분하다. 하지만 받침이라는 개념이 존재하는 한글은 적어도 2Byte는 필요하다. 또한, 중국어 같은 경우에는 3Byte 까지도 필요하다고 한다.

그래서 우리는 UTF-8 이라는 한 문자당 3Byte로 통신하는 규약을 사용한다.

자바는 InputStream을 통해 1Byte 단위로 문자를 읽어들인다. Byte로 받아들인다면 해당 데이터를 char로 형 변환 해주어야 한다. 이 역할을 한번에 해주는 것이 InputStreamReader 클래스이다. 또한, 배열로 여러 개의 문자를 받을 수 있다. 배열은 단점이 크기가 정해져 있어야 한다. 적게 잡으면 문자가 잘리고 넓게 잡는다면 데이터 공간이 낭비될 수 있다.

그래서 가변길이 문자열을 받을 수 있는 BufferedReader를 사용한다. BufferedWriter는 반대의 역할을 한다.

스프링에서는 `@ResponseBody`가 BufferedReader의 역할을 하며, `@RequestBody`는 BufferedReader의 역할을 한다.

> @ResponseBody 를 붙여서 응답하게 되면 해당 응답을 페이지가 아닌 데이터로 해석한다.

### Apache & Tomcat

먼저 소켓 통신에 대해 알아보자. 

예를 들어 A 라는 대상과 B 라는 대상이 있는데 서로 메시지를 교환하고 싶다. 메시지를 교환하고 싶다면 운영체제가 가지고 있는 소켓을 사용하여야 한다. A가 5000번 포트를 열고 소켓을 오픈한다. B가 A와 통신하고 싶다면 `ip주소:5000` 으로 연결을 한다.

이때, C가 A와 통신을 하고 싶다면 동일한 5000번 포트로 연결을 할 수 없다. 이 때문에 5000번 포트는 최초 연결 용도로만 사용한다.

연결된 B 사용자는 5000번 포트가 끊기고 5001번 포트로 다시 연결되며 쓰레드를 1개 만든다. 이것은 메인 스레드의 역할이다. 요청이 들어올 때 마다 통신을 하기 위해서 새로운 쓰레드를 만든다. 

이러한 소켓 통신은 연결이 계속 유지가 되기 때문에 부하가 크다.

HTTP 통신은 소켓을 기반으로 만들어졌는데, STATELESS하다. HTTP/1.1에서는 기본적으로 지속 연결을 사용한다. 지속 연결이란 클라이언트와 서버 간의 하나의 TCP 연결을 통해 여러 요청과 응답을 주고받을 수 있다는 것을 의미한다. 따라서 요청 후 응답이 끝난 후에도 연결은 유지된다.

그러나, HTTP는 여전히 무상태 프로토콜이다. 이는 서버가 각각의 HTTP 요청을 독립적으로 처리하고, 이전 요청의 상태를 기억하지 않는다는 것을 의미한다. 같은 사람이 여러 번 요청을 하더라도, 서버는 이를 새로운 요청으로 인식한다. 상태 정보를 유지하려면 클라이언트가 요청마다 쿠키나 세션 ID와 같은 상태 정보를 함께 보내야 한다.

요약하면, HTTP/1.1에서는 지속 연결을 통해 동일한 연결을 유지하면서 여러 요청을 처리할 수 있지만, 각 요청은 여전히 독립적으로 처리되기 때문에 상태는 유지되지 않는다.

Apache는 이러한 HTTP/1.1 통신을 기반으로 하며 요청 시 static(정적인) 파일(.html, .png 등)을 응답 해준다.  요청은 `URL : IP주소` 와 같은 방식으로 이루어진다.

- URL : 자원 접근 방법 ex) http://naver.com/a.png -> a.png라는 자원을 요청한다.
- URI : 식별자 접근 방법 ex) http://naver.com/picture/a -> picture이라는 식별자로 a라는 picture(사진)을 요청

만약 .JSP 라던지 자바 코드가 적혀있는 것으로 요청을 하게 되면 Apache는 이것을 이해하지 못한다. 그래서 여기에 Tomcat이라는 것을 붙여 사용한다. Apache가 이해하지 못하는 파일의 요청이 오면 Tomcat에게 제어권을 넘긴다. 이때 Tomcat은 자바 코드를 컴파일 하여 html 문서로 반환해준다. 이것을 Apache가 받아 응답을 하게 된다. 즉, Tomcat의 역할은 동적인 데이터를 처리할 수 있고 DB 연결 데이터 조작, 다른 응용 프로그램과의 상호작용을 가능하게 한다.

### Servlet 객체의 생명주기

서블릿 컨테이너란 스프링의 내장 Tomcat 서버를 의미한다. 

서블릿 컨테이너는 이러한 역할을 한다.

**첫번째 요청시**

1. 서블릿 컨테이너에게 요청이 전달됨
2. 서블릿 컨테이너가 서블릿 객체를 생성
3. 서블릿 객체는 `init()`을 호출
4. `Service() -> 어느 http 요청인지 확인` 호출 직전에 새로운 스레드를 생성
5. 해당 스레드는 `Service()` 안의 http 메서드(get, post, put, delete 등)를 실행하여 DB 연결, 데이를 응답

**두번째 요청시**

1. 이미 생성되어있는 서블릿 객체를 재사용
2. 서블릿 객체 재사용하므로 init 메서드를 실행하지 않음
3. `Service()` 호출 직전에 새로운 스레드를 생성하여 http 요청에 따라 메서드를 실행

다수의 요청이 동시 접근할 경우 요청의 개수만큼 스레드를 생성하게 되는데 컴퓨터 성능에 따라 스레드를 생성할 수 있는 개수가 다르다. 예를 들어 20개의 스레드만 생성할 수 있는 컴퓨터에 25개의 요청이 들어왔다고 했을때 넘치는 5개의 요청은 대기를 시키고 20개 중 응답을 마친 스레드를 소멸시키지 않고 대기 중인 요청을 위해 재사용한다. 스레드를 재사용하는 것을 `pooling` 이라고 한다.

### web.xml

web.xml은 톰캣의 필터이다.

- ServletContext의 초기 파라미터
	- 애플리케이션 전역에서 접근 가능하여 어디든지 사용 가능
- Session의 유효기간 설정
	- 인증정보의 유효기간을 설정한다.
- Servlet/JSP에 대한 정의와 매핑
	- 사용자가 요청한 자원의 위치로 이동할 수 있게 도와준다.
- Mime Type의 매핑
	- Mime Type이란 받아올 데이터 타입이다. 해당 데이터 타입을 서버에게 알려준다.
- Welcome File List
	- 사용자가 특정 목적 없이 웹 애플리케이션에 처음 들어왔을 때 기본적으로 보여줄 페이지를 정의한다.
- Error Pages 처리
	- 사용자가 웹 애플리케이션을 이용하는 도중 오류가 발생했을 때 기본적으로 보여줄 에러 페이지를 정의한다.
- 리스너/필터 설정
	- 필터: 올바른 인증 정보에 맞지 않는 접근을 막음. 요청과 응답을 가로채고 수정할 수 있는 기능을 제공하여 보안, 로깅, 데이터 압축 등을 수행할 수 있다.
	- 리스너: 특정 조건을 만족하는 이벤트를 캐치하는 것. 웹 애플리케이션의 생명주기, 세션의 생성 및 소멸, 요청의 시작 및 종료 등 다양한 이벤트를 감지하고 처리할 수 있다.
- 보안
	- 비정상적인 접근에 대한 처리가 보안이다.

### DispatcherServlet

`FrontController` 패턴은 웹 애플리케이션에서 최초로 앞 단에서 요청을 받아 필요한 클래스에 넘겨주는 구조를 말한다. 이를 통해 각 요청마다 web.xml에 개별적으로 정의하지 않아도 된다.

처리 흐름은 이렇다.

1. 클라이언트로부터 요청이 들어오면 톰캣 서버는 request와 response 객체를 생성한다.
2. 특정 주소로 요청이 들어오면 모든 요청이 FrontController로 전달됩니다.
3. FrontController는 해당 요청을 처리할 적절한 내부 자원(서블릿, JSP)으로 요청을 전달합니다.

여기서 문제가 있다. FrontController에서 내부 자원으로 요청을 다시 전달하게 되면 기존 톰캣에서 생성하였던 request와 response 객체의 정보가 사라질 우려가 있다.

이를 방지하기 위하여 `RequestDispatcher`를 사용하여 기존 request와 response 객체를 유지하며 요청을 포워딩 한다. 이렇게 기존의 request와 response 객체를 유지함으로써 페이지 간 데이터 이동이 가능하게 한다.

위의 두 기능을 묶은 것이 바로 DispatchServlet이다. 스프링 애플리케이션에서 `DispatchServlet`은 자동으로 생성되며, 이를 통해 웹 애플리케이션의 요청 처리 구조를 설정할 수 있다 이 것으로 인해 FrontController 패턴, RequsetDispatcher를 직접 구현할 필요가 없어진다. `DispatchServlet`이 생성될 때, 스프링 IoC 컨테이너에 의해 수많은 객체가 자동으로 생성되고 관리된다. 기본적으로 필요한 필터들이 자동으로 등록되며, 추가 필터는 개발자가 직접 작성도 가능하다.

### Application Context

- **`ContextLoaderListener`**:
    - 웹 애플리케이션의 루트 애플리케이션 컨텍스트를 초기화하고 설정한다. 이는 공통 객체(예: 데이터베이스 커넥션 풀 등)를 관리한다.
    - `web.xml`에서 `ContextLoaderListener`를 설정하여 `root-applicationContext.xml` 또는 Java Config를 통해 설정된 빈들을 로드한다.
    - `ContextLoaderListener`는 `DispatchServlet`이 초기화되기 전에 먼저 실행된다.
    - 이로 인해 `ContextLoaderListener`는 `DispatchServlet`의 메모리에 직접 접근하지 못하지만, `DispatchServlet`은 `ContextLoaderListener`의 메모리에 접근할 수 있습니다.

- **`DispatchServlet`**:
    - 요청을 받아 처리하고 적절한 핸들러(컨트롤러)로 요청을 분배한다.
    - 애플리케이션의 클래스를 스캔하고 필요한 빈들을 메모리에 로드한다.
    - 일반적으로 특정 패키지(예: `com.example.demo`) 내의 자바 클래스들을 스캔하여 인스턴스를 생성합니다.

- **IoC 컨테이너**:
    - `ContextLoaderListener`는 루트 애플리케이션 컨텍스트를 통해 공통적인 빈들을 관리하고, `DispatchServlet`은 별도의 서블릿 컨텍스트를 통해 요청 처리에 필요한 빈들을 관리한다.
    - 빈은 싱글톤으로 관리되며, 의존성 주입을 통해 객체를 제공한다.