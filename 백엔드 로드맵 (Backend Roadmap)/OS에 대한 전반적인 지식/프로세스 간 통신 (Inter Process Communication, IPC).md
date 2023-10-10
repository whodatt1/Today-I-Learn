### IPC의 개념

![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F2641923B5718784D35)
> 리눅스 터널 구조 (출처 : https://jwprogramming.tistory.com/54)

위 그림처럼 프로세스(Process)는 ==완전히 독립된 실행 객체== 입니다. 서로 독립되어 있다는 것은 다른 프로세스의 영향을 받지 않는다는 장점이 있지만, 독립되어 있는 만큼 별도의 설비가 없이는 서로 간의 통신이 어렵다는 문제가 있습니다.

이를 해결하기 위해 **커널 영역**에서 IPC 내부 프로세스 간 통신을 
