### IPC의 개념

![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F2641923B5718784D35)
> 리눅스 터널 구조 (출처 : https://jwprogramming.tistory.com/54)

위 그림처럼 프로세스(Process)는 ==완전히 독립된 실행 객체== 입니다. 서로 독립되어 있다는 것은 다른 프로세스의 영향을 받지 않는다는 장점이 있지만, 독립되어 있는 만큼 별도의 설비가 없이는 서로 간의 통신이 어렵다는 문제가 있습니다.

이를 해결하기 위해 **커널(Kernel) 영역**에서 IPC 내부 프로세스 간 통신을 제공하게 되고, 프로세스는 커널이 제공하는 설비를 이용하여 프로세스 간 통신을 가능하게 합니다.

-  **커널(Kernel)** 이란?

> 운영체제 자체도 소프트웨어이기 때문에 메모리에 올라가야 사용 가능하다. 하지만 메모리 공간의 제약으로 운영체제 중 항상 필요한 부분 만을 메모리에 올려놓고, 그렇지 않은 부분은 **필요할때만** 메모리에 올려서 사용하게 된다. 이 때 메모리에 상주하는 운영체제의 핵심 적인 부분을 커널이라 한다.

### IPC의 종류

### 1. 공유 메모리 (Shared Memory)

![](https://t1.daumcdn.net/cfile/tistory/99A8064C5AD5EFB82C)
> 공유 메모리 (출처 : https://t1.daumcdn.net/cfile/tistory/99A8064C5AD5EFB82C)

-  커널에 생성된 공유 메모리를 통해서 프로세스 간 데이터를 공유합니다.
-  프로세스가 공유 메모리 할당을 커널에 요청하면 커널은 해당 프로세스에 메모리 공간을 할당합  니다. 이후 어떤 프로세스건 해당 메모리 영역에 접근 할 수 있습니다.
-  대량의 정보를 다수의 프로세스에게 배포 가능합니다.
-  중개자 없이 곧바로 메모리에 접근 할 수 있으므로 모든 IPC 중에서 가장 빠르게 작동합니다.
-  ==단순히 공유 메모리를 Point 함으로써 프로세스에서 사용되는 메모리가 증가 되지 않는다.==

### 2.  파이프 (Pipe)

![](https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F247CBC4357187A3411)
> 파이프 (출처 : https://img1.daumcdn.net/thumb/R1280x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F247CBC4357187A3411)

통신을 위한 **메모리 공간(버퍼)** 을 생성하여 프로세스가 데이터를 주고 받을 수 있게 합니다.

1.  익명 파이프 (Anonymous PIPE)

-  두 개의 프로세스를 연결하게 되고, 하나의 프로세스는 데이터를 쓰기만, 다른 하나는 데이터를 읽기만 할 수 있습니다. 한쪽 방향으로만 통신이 가능한 파이프의 특징 때문에 Duplex(반 이중) 통신이라고 부르기도 합니다.
-  송/수신을 모두 하기 원한다면 두 개의 파이프를 만들어야만 가능해집니다.
-  장점
	-  매우 간단하게 사용할 수 있습니다. 만약 한쪽 프로세스가 단지 읽기만 하고 다른 쪽 프로세스는 단지 쓰기만 하는, 단순한 데이터의 흐름을 가진다면 이 파이프를 사용하면 됩니다.
-  단점
	-  반 이중 통신이라는 점.  만약 프로세스가 읽기와 쓰기를 모두 해야 한다면 파이프를 두 개 만들어야 하는데 구현이 꽤 복잡해질 가능성이 있습니다.

2.  네임드 파이프 (Named PIPE)

-  전혀 모르는 프로세스들 사이의 통신의 경우 사용합니다. 예를 들면 자식과 부모 프로세스 간 통신의 경우
-  익명 파이프의 단점으로 같은 PPID(같은 부모 프로세스)를 가지는 프로세스들 사이에서만 통신이 가능하지만, 네임드 파이프는 그 부분을 해결한, 익명 파이프의 확장이라고 할 수 있습니다.
-  장점
	-  네임드 파이프는 부모 프로세스와 무관하게 전혀 다른 모든 프로세스들 사이에서 통신이 가능합니다. 그 이유는 프로세스 통신을 위해 이름이 있는 파일을 사용하기 때문입니다.
-  단점
	-  익명 파이프와 동일하게 읽기/쓰기가 동시에 가능하지 않습니다. read-only 혹은 write-only만 가능합니다. 하지만 통신 선로가 파일로 존재하므로 하나를 읽기 전용으로 열고 다른 하나를 쓰기 전용으로 열어서 이러한 문제를 해결 할 수 있습니다.
	-  전 이중 통신을 위해서는 결국 익명 파이프와 같이 두 개의 FIFO 파일을 필요하게 됩니다.

### 3.  메시지 큐 (Message Queue)

![](https://mblogthumb-phinf.pstatic.net/MjAyMDA4MDJfMTgw/MDAxNTk2MzU0NTMyMDAy.9sHra7poMm6WEOD92dZgqFgDEx57Gs5WHe-RjuFJm2kg.l52BvVDYDyaHS9OEYtgMp04zPTEdzuF7UjAbM4Dg8OYg.PNG.dkwlsrhstm/image.png?type=w800)
>  메시지 큐 (출처 : https://mblogthumb-phinf.pstatic.net/MjAyMDA4MDJfMTgw/MDAxNTk2MzU0NTMyMDAy.9sHra7poMm6WEOD92dZgqFgDEx57Gs5WHe-RjuFJm2kg.l52BvVDYDyaHS9OEYtgMp04zPTEdzuF7UjAbM4Dg8OYg.PNG.dkwlsrhstm/image.png?type=w800)

-  선입선출의 자료 구조를 가지는 통신 설비로 커널에서 관리합니다. 입출력 방식으로 보자면 네임드 파이프와 동일하다고 할 수 있습니다.
-  네임드 파이프와 다른 점이 있다면 네임드 파이프가 데이터의 흐름이라면 메시지 큐는 메모리 공간이라는 점 입니다. 파이프가 아닌, 어디에서나 물건을 꺼낼 수 있는 컨테이널 벨트라고 볼 수 있습니다.

### 4.  소켓 (Socket)

![](https://velog.velcdn.com/images%2Fchappi%2Fpost%2Ff6d8042a-1393-469e-b0f4-afc4ca908cd9%2F6.png)
> 소켓 (출처 : https://velog.velcdn.com/images%2Fchappi%2Fpost%2Ff6d8042a-1393-469e-b0f4-afc4ca908cd9%2F6.png) 

-  클라이언트와 서버가 소켓을 통해서 통신하는 구조로 원격에서 프로세스 간 데이터를 공유할 때 사용됩니다.
-  전이중(Full Duplex, 양방향) 통신이 가능합니다.
-  네트워크 소켓 통신을 통해 데이터를 공유합니다.
	-  데이터 교환을 위해 양쪽 PC에서 각각 임의의 포트를 정하고 해당 포트 간의 대화를 통해 데이터를 주고받는 형식입니다.
	-  이때 각각 PC의 포트를 담당하는 소켓은 각각 하나의 프로세스입니다.
	-  즉 해당 프로세스는 임의의 포트를 맡아 데이터를 송수신 하는 역할을 가진 프로세스입니다.
	-  각각의 PC에서 프로세스를 통해 타 PC 포트에 연결하라는 명령을 보내면 두 프로세스는 확인 과정을 거친 후 연결되어 마치 파이프와 같이 1 : 1 로 데이터를 주고받는 방식이 됩니다.
-  여러 컴퓨터에 있는 프로세스 간 통신은 네트워킹이며, 네트워크의 기본은 소켓이기 때문에 네트워크 프로그래밍을 소켓 프로그래밍이라고도 합니다.
-  네트워킹 상황에서의 통신은 소켓 이외의 원격 프로시저 호출(RPC)을 사용하는데 원격 프로시저 호출은 다른 컴퓨터에 있는 함수를 호출하는 것입니다. 자바 같은 경우 다른 컴퓨터에 있는 객체의 메서드를 호출하는 것이 원격 프로시저 호출입니다. 일반적으로 원격 프로시저 호출은 소켓을 이용하여 구현합니다.

### 5.  메모리 맵 (Memory Map)

![](https://t1.daumcdn.net/cfile/tistory/9951304F5B9863F82D)

-  공유 메모리처럼 메모리를 공유해줍니다.
-  메모리 맵은 열린 파일을 메모리에 매핑시켜서 공유하는 방식입니다. (즉 공유 매개체가 파일 + 메모리)
-  메모리 맵 파일은 파일의 크기는 메모리 맵 파일을 사용하기 이전 또는 이후에만 파일의 크기를 변경 할 수 있습니다.

### 6.  세마포어 (Semaphore)

-  다른 IPC 설비들이 대부분 프로세스 간 메시지 전송을 목적으로 하는데 반해, 세마포어는 프로세스 간 데이터를 동기화하고 보호하는데 목적을 둡니다.
-  공유된 자원에 여러개의 프로세스가 동시에 접근하면 안되며, 한번에 하나의 프로세스만 접근 가능하도록 할 때 사용합니다.

### 참고한 블로그

-  https://dar0m.tistory.com/233
-  https://jwprogramming.tistory.com/54
-  https://velog.io/@chappi/OS%EB%8A%94-%ED%95%A0%EA%BB%80%EB%8D%B0-%ED%95%B5%EC%8B%AC%EB%A7%8C-%ED%95%A9%EB%8B%88%EB%8B%A4.-7%ED%8E%B8-IPC-%ED%94%84%EB%A1%9C%EC%84%B8%EC%8A%A4-%EA%B0%84-%ED%86%B5%EC%8B%A0
-  https://velog.io/@yanghl98/OS%EC%9A%B4%EC%98%81%EC%B2%B4%EC%A0%9C-IPC%EB%9E%80
-  https://doitnow-man.tistory.com/68