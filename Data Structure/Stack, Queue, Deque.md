### 스택(Stack)

선형 구조의 형태이며 데이터를 일시적으로 쌓아두기 위한 자료 구조 중 하나이다. 후입선출(LIFO, Last-In-First-Out)의 특성을 가지고 있다.

> 후입선출(LIFO, Last-In-First-Out)
> 가장 마지막에 추가된 데이터가 가장 먼저 삭제되는 구조를 의미한다.

-  함수 콜 스택, 괄호를 계산하는 수식계산, 웹 브라우저 방문 기록 등이 스택을 사용하는 대표적인 예로 들 수 있다.
-  깊이 우선탐색(DFS:Depth First Search), 백트래킹 종류의 코딩테스트에 사용된다.
-  java.util.Stack

![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FslKK5%2FbtsDKcgRQKX%2F9TteTXUUNIBqL8vQ5IkIH1%2Fimg.png)

**top**
삽입 삭제가 이루어지는 위치를 의미한다.

**push**
스택의 최상위 위치(top)에 데이터를 추가하는 연산이다. Java에선 push(T t) 메서드를 제공한다.

**pop**
스택의 최상위 위치(top)에서 데이터를 삭제 후 반환하는 연산이다. Java에선 pop() 메서드를 제공한다.

**peek**
스택의 최상위 위치(top)에 있는 데이터를 조회하는 연산이다. Java에선 peek() 메서드를 제공한다.

### 큐(Queue)

선형 구조의 형태이며 데이터를 일시적으로 쌓아두기 위한 자료 구조 중 하나이다. 선입선출(FIFO, First-In-First-Out)의 특성을 가지고 있다.

> 선입선출(FIFO, Fist-In-First-Out)
> 가장 먼저 추가된 데이터가 가장 먼저 삭제되는 구조를 의미한다.

-  Java에서 큐는 인터페이스의 역할을 수행하며 ArrayDeque, LinkedList, PriorityQueue 등을 통해 구현체를 구현한다.

![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2FmTm4F%2Fbtr1QyhCtBn%2F1KTgwXKxnVuLcTazteaq3k%2Fimg.png)

-  큐에서의 삽입(Enqueue)와 추출(Dequeue) 과정은 front와 rear 포인터를 기준으로 작동한다. 데이터가 저장되는 부분은 rear 부분이며 데이터가 추출되는 부분은 front이다.

**Enqueue**
큐의 맨 뒤(rear)에서 데이터를 추가하는 연산을 의미합니다. Java에서는 add(E e), offer(E e) 메서드를 제공한다.

**rear**
큐의 맨 뒤에 위치한 데이터를 가리키는 포인터이다. Java에서는 remove(), poll() 메서드를 제공한다.

**front**
큐의 맨 앞에 위치한 데이터를 가리키는 포인터이다. Java에서는 element(), peek() 메서드를 제공한다.

**Dequeue**
큐의 맨 앞의 위치한 데이터를 가리키는 포인터이다.

### 덱(Deque)

선형 구조의 형태이며 데이터를 일시적으로 쌓아두기 위한 자료 구조 중 하나이다. 양쪽 끝에서 삽입 삭제가 가능하다.

-  덱(Deque)은 스택과 큐의 기능을 모두 가지고 있는 자료구조이며, 양쪽 끝에서 삽입과 삭제가 가능하다. 선입선출과 후입선출 개념이 모두 적용 될 수 있다.

![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Fblog.kakaocdn.net%2Fdn%2Fc2mvuK%2Fbtr16khBqJq%2FlSWjtXVPYPrlRKDeE8DAy1%2Fimg.png)

-  양방향 큐, 팰린드롬 판별, 최대값/최소값 검색이 사용예시가 될 수 있겠다.

**덱(Deque)의 메서드**

**addFirst(E e)**
덱의 맨 앞에 지정된 요소를 추가한다.

**addLast(E e)**
덱의 맨 뒤에 지정된 요소를 추가한다.

**removeFrist()**
덱의 맨 앞에서 요소를 제거하고 반환한다. 덱이 비어있다면 예외 발생

**removeLast**
덱의 맨 뒤에서 요소를 제거하고 반환한다. 덱이 비어있다면 예외 발생

**getFirst()**
덱의 맨 앞에서 요소를 반환한다. 덱이 비어있다면 예외 발생

**getLast()**
덱의 맨 뒤에서 요소를 반환한다. 덱이 비어있다면 예외 발생