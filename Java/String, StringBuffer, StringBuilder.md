### String의 특징

-  인스턴스 생성 시 생성자의 매개변수로 입력받는 문재열은 아래 value라는 인스턴스 변수에 문자형 배열로 저장되게 된다. 이 value라는 변수는 상수(final)형이라 값을 바꾸지 못한다. 이는 한번 할당된 공간이 변하지 않는다고 해서 불변(immutable) 자료형이라고 불리운다.
```java
public final class String implements java.io.Serializable, Comparable {
	private final byte[] value;
}
```
-  String 객체의 경우 매번 문자열이 업데이트 될 때마다 계속해서 메모리 블럭이 추가되게 되고, 일회용으로 사용된 메모리들은 후에 Garbage Collector(GC)의 제거 대상이 된다
-  문자열 연산 시 새로 객체를 만드는 Overhead를 발생시킨다.

아래 예제 코드를 봤을 때 변수 str이 참조하는 메모리의 "hello"라는 값에 " world"라는 문자열을 더해서 String 객체의 자체의 값을 업데이트 시킨 것처럼 보인다.

하지만 실제로는 메모리에 새로 "hello world" 값을 저장한 영역을 따로 만들고 변수 str을 다시 참조하는 식으로 작동한다.
```java
String str = "hello"; // GC 제거 대상
str = str + " world";

System.out.println(str); // hello world
```

-  객체가 불변하므로, 멀티 쓰레드에서 동기화를 신경 쓸 필요가 없다. (조회 연산에 매우 큰 장점)

> 내부 상태가 변경 되지 않기에 여러 쓰레드가 동시에 이 객체를 사용하여도 상태의 변화가 없기에 동기화가 필요하지 않음

### StringBuffer, StringBuilder 특징

-  문자열을 다룬다는 점에서 String 객체와 같지만 두 클래스는 내부 Buffer(데이터를 임시로 저장하는 메모리)에 문자열을 저장해두고 그 안에서 추가, 수정, 삭제 작업을 할 수 있도록 설계되어 있다. StringBuffer/StringBuilder는 가변적이기 때문에 .append(), .delete() 등의 API를 이용하여 동일 객체 내에서 문자열의 크기를 변경 가능하다.

StringBuffer의 내부 구조를 보면 상수(final) 키워드가 없는 것을 확인할 수 있다.
```java
public final class StringBuffer implements java.io.Serializable {
	private byte[] value;
}
```
-  StringBuffer와 StringBuilder의 차이점은 동기화의 지원 유무로 StringBuffer는 동기화를 지원하여 Thread-Safe 하며, StringBuilder는 지원하지 않아 Thread-Safe 하지 않다.

StringBuffer 클래스 : 문자열 연산이 많은 Multi-Thread 환경
StringBuilder 클래스 : 문자열 연산이 많은 Single-Thread 혹은 Thread를 신경쓰지 않는 환경