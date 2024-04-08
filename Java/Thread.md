OS는 모두 멀티태스킹을 지원한다.

>멀티태스킹이란 두 가지 이상의 작업을 동시에 하는 것
>예를 들면, 음악을 들으며 게임을 하거나 웹 서핑을 하는 경우이다.

실제로 동시에 처리될 수 있는 프로세스의 개수는 CPU의 코어 개수와 동일하다.

각 코어들은 아주 짧은 시간 간격을 갖고 여러 프로세스를 번갈아가며 처리하는 방식을 통하여 동시에 동작하는 것처럼 보이게 한다.

멀티스레딩이란 하나의 프로세스 안에 여러개의 스레드가 동시에 작업을 수행하는 것을 말한다. 스레드를 하나의 작업단위라고 말할 수 있다.

아래에 기술한 것들은 Java를 기반으로 한다.

## 스레드 구현

자바에서 스레드 구현 방법은 두가지로 나뉜다.

1. Runnable 인터페이스 구현
2. Thread 클래스 상속

두 가지 전부 run() 메서드를 오버라이딩 하는 방식이다.

```java
public class ThreadEx implements Runnable {
	@Override
	public void run() {
		// 수행 코드
	}
}

public class ThreadEx extends Thread {
	@Override
	public void run() {
		// 수행 코드
	}
}
```

## 쓰레드 생성

위의 두가지 방법은 인스턴스 생성 방법에 차이가 있다.
Runnable 인터페이스를 구현한 경우는, 해당 클래스를 인스턴스화 해서 Thread 생성자에 인수로 넘겨주어야 한다.

```java
public static void main(String[] args) {
	Runnable r = new ThreadEx();
	Thread t = new Thread(r, "threadex");
}
```

Thread 클래스를 상속받은 경우는, 상속받은 클래스 자체를 스레드로 사용할 수 있다.

```java
public static void main(String[] args) {
	ThreadEx t = new ThreadEx();
}
```

또한, Thread 클래스를 상속받으면 스레드 클래스의 메소드 getName()을 바로 사용할 수 있지만, Runnable 구현의 경우 Thread 클래스의 static 메서드인 currentThread()를 호출하여 현재 스레드에 대한 참조를 얻어와야만 호출이 가능하다.

```java
public class ThreadTest implements Runnable {
	public ThreadTest() {}

	public ThreadTest(String name) {
		Thread t = new Thread(this, name);
		t.start();
	}

	@Override
	public void run() {
		for (int t = 0; i <= 50; i++) {
			System.out.println(i + ":" + Thread.currentThread().getName() + " ") // Thread 상속의 경우 Thread.getName() 으로 바로 접근 가능
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
```

## 스레드 실행

> 스레드의 실행은 run() 호출이 아닌 start() 호출로 해야한다.

Java에는 콜 스택(Call Stack)이 있다. 이 영역이 실질적인 명령어들을 담고 있는 메모리로, 하나씩 꺼내서 실행시키는 역할을 한다.
만약 동시에 두 가지 작업을 수행한다면, 두 개 이상의 콜 스택이 필요로 하게 된다.
**스레드를 이용한다는 건, JVM이 다수의 콜 스택을 번갈아가며 일처리**를 하고 사용자에게는 동시에 작업이 진행되는 것처럼 보인다.
run() 메서드를 이용한다는 것은 main()의 콜 스택 하나만 이용하는 것으로 스레드의 활용이라고 볼 수 없다.
start() 메서드를 호출하면 JVM은 알아서 스레드를 위한 콜 스택을 새로 만들어주고 context switching을 통하여 스레드답게 동작하도록 해준다.

## 스레드의 실행제어

> 스레드의 상태는 5가지가 있다.

-  NEW : 스레드가 생성되고 아직 start()가 호출되지 않은 상태
-  RUNNABLE : 실행 중 또는 실행 가능 상태
-  BLOCKED : 동기화 블럭에 의해 일시 정지된 상태(lock이 풀릴 때까지 기다리게 됨)
-  WAITING, TIME_WAITING : 실행 가능하지 않은 일시 정지 상태
-  TERMINATED : 스레드 작업이 종료된 상태

스레드 구현하는 것이 어려운 이유는 바로 동기화와 스케줄링 때문이다.
스케줄링과 관련된 메서드는 sleep(), join(), yield(), interrupt()와 같은 것들이 있다.
start() 이후에 join()을 해주면 main 스레드가 모두 종료될 때까지 기다려주는 일도 해준다.

## 동기화

멀티스레드로 구현을 하다보면 동기화는 필수적이다.
동기화가 필요한 이유는 **여러 스레드가 같은 프로세스 내의 자원을 공유하면서 작업할 때 서로의 작업이 다른 작업에 영향을 주기 때문**이다.
스레드 동기화를 위해선 임계영역(critical section)과 잠금(lock)을 활용한다.
임계영역을 지정하고, 임계영역을 가지고 있는 lock을 단 하나의 스레드에게만 빌려주는 개념으로 이루어져 있다.
따라서 임계구역 안에서 수행할 코드가 완료되면 lock을 반납해줘야 한다.

### 스레드의 동기화 방법

-  임계영역(critical section) : 공유 자원에 단 하나의 스레드만 접근 하도록 (하나의 프로세스에 속한 스레드만 가능)
-  뮤텍스(mutex) : 공유 자원에 단 하나의 스레드만 접근하도록(서로 다른 프로세스에 속한 스레드도 가능)
-  이벤트(event) : 특정한 사건 발생을 다른 스레드에게 알림
-  세마포어(semaphor) : 한정된 개수의 자원을 여러 스레드가 사용하려고 할 때 접근 제한
-  대기 기능 타이머(waitable timer) : 특정 시간이 되면 대기 중이던 스레드 깨움

### synchronized 활용

> synchronized를 활용하여 임계영역을 설정할 수 있다.

서로 다른 두 객체가 동기화를 하지 않은 메서드를 같이 오버라이딩해서 이용하면, 두 스레드가 동시에 진행되므로 원하는 출력 값을 얻지 못한다.
이때 오버라이딩되는 부모 클래스의 메소드에 synchronized 키워드로 임계영역을 설정해주면 해결할 수 있다.

```java

private int money;
//synchronized : 스레드의 동기화. 공유 자원에 lock
public synchronized void saveMoney(int save) { // 입금 메서드
	int money = money;
	try {
		Thread.sleep(2000); // 지연시간 2초
	} catch (Exception e) {}

	money = money + save;
	System.out.println("입금 처리");
}

public synchronized void minusMoney(int minus) { // 출금 메서드
	int money = money;
	try {
		Thread.sleep(3000); // 지연시간 3초
	} catch (Exception e) {}

	money = money - minus;
	System.out.println("출금 처리");
}
```

### wait()과 notify() 활용

> 스레드가 서로 협력관계일 경우에는 무작정 대기시키는 것으로 올바르게 실행되지 않기 때문에 사용한다.

-  wait() : 스레드가 lock을 가지고 있으면 lock 권한을 반납하고 대기하게 만듦
-  notify() : 대기 상태인 스레드에게 다시 lock 권한을 부여하고 수행하게 만듦

이 두 메서드는 동기화 된 영역(임계영역) 내에서 사용되어야 한다.

동기화 처리한 메소드들이 반복문에서 활용된다면, 의도한대로 결과가 나오지 않는다. 이때 wait()과 notify()를 try-catch 문에서 적절히 활용하여 해결할 수 있다.

```java
public synchronized void makeBread() {
	if (breadCnt >= 10) {
		try {
			System.out.println("빵 생산 초과");
			wait(); // Thread를 Not Runnable 상태로 전환
		} catch (Exception e) {}
	}
	
	breadCnt++; // 빵 생산
	System.out.println("빵을 만듦. 총 " + breadCnt + "개");
	notify(); // Thread를 Runnable 상태로 전환
}

public synchronized void eatBread() {
	if (breadCnt < 1) {
		try {
			System.out.println("빵이 없음");
			wait(); // Thread를 Not Runnable 상태로 전환
		} catch (Exception e) {}
	}
	
	breadCnt--; // 빵 취식
	System.out.println("빵을 먹음. 총 " + breadCnt + "개");
	notify(); // Thread를 Runnable 상태로 전환
}
```

조건 만족 안할 시 wait(), 만족 시 notify()를 받아 수행