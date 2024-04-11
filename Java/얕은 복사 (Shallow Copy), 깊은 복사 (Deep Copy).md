얕은 복사(Shallow Copy)는 주소 값(스택 영역)을 복사하기 때문에 참조하고 있는 실제 값은 같다.
깊은 복사(Deep copy)는 실제 값을 메모리 공간(힙 영역)에 복사하기 때문에 참조하고 있는 실제 값이 다르다.

아래와 같은 클래스가 있다고 가정해보자.
```java
public class User {
	private String name;
	private int age;

	public User(String name, int age) {
		this.name = name;
		this.age = age;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge() {
		this.age = age;
	}
	
	@Override
	public String toString() {
		return this.name + " " + this.age;
	}
}
```

### 얕은 복사 (Shallow Copy)

```java
void shallowCopy() {
	User user = new User("아무개", 25);
	User copyUser = user;

	copyUser.setName("아무개2");
	copyUser.setAge(24);

	System.out.println(user.toString()); // 아무개2 24
	System.out.println(copyUser.toString()); // 아무개2 24
}
```

위와 같이 출력되는 이유는 User copyUser = user; 코드에서 user의 참조값을 복사했기 때문이다.

copyUser의 값을 수정할 경우 copyUser가 참조하고 있는 user와 같이 공유하고 있는 데이터의 값이 수정된다.
실제 데이터를 복사하기 원한다면 깊은 복사(Deep Copy)가 필요하다.

### 깊은 복사 (Deep Copy)

깊은 복사를 구현하는 방법에는 대표적으로 3 가지가 있다.

-  복사 생성자 또는 복사 팩토리를 이용하여 복사하는 방벙
-  직접 객체를 생성하여 복사하는 방법
-  Clonable 인터페이스를 구현하여 clone() 함수를 오버라이딩하여 복사하는 법

> clone() 함수를 재정의하는 방법은 final 클래스가 아닌 경우 문제가 발생할 소지가 있다.

1. 복사 생성자 또는 복사 팩토리를 이용하여 복사하는 방법

```java
// 복사 생성자
public User(User user) {
	this.name = user.name;
	this.age = user.age;
}

// 복사 팩토리
public static User copyFactory(User user) {
	User copyUser = new User(user.name, user.age);
	return copyUser
}
```

2. 직접 객체를 생성하여 복사하는 방법
```java
void deepCopy() {
	User user = new User("아무개", 25);
	User copyUser = new User(user.getName(), user.getAge());
}
```

위와 같이 깊은 복사를 통해 객체를 복사할 경우 참조 값이 아닌 실제 데이터를 복사한 것을 확인 할 수 있다.

```java
copyUser.setName("아무개2");
copyUser.setAge(24);

System.out.println(user.toString()); // 아무개 25
System.out.println(copyUser.toString()); // 아무개2 24
```
