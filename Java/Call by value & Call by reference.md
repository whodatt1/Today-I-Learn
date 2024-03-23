## Call by value

> 값에 의한 호출

함수가 호출 될 때, 메모리 공간 안에서는 함수를 위한 별도의 임시 공간이 생성되며, 종료 시에는 해당 공간이 사라진다. Call by value 호출 방식은 함수 호출 시 전달되는 변수 값을 복사해서 함수 인자로 전달한다. 이 때 복사 된 인자는 함수 안에서 지역적으로 사용되기 때문에 local value 속성을 가진다. 자바의 경우 항상 Call by value로 값은 넘긴다. 원본 객체의 property 까지는 접근이 가능하나, 원본 객체 자체를 변경할 수는 없기 때문이다.

-  따라서, 함수 안에서 인자 값이 변경되더라도, 외부 변수 값은 변경되지 않는다.

### 예시 (Java code)

```java
public class Main {

	public static void main(String[] args) {
		int n = 10;
		
		func(n);
		
		System.out.println(n); // 10
	}
	
	static void func(int n) {
		n = 20;
	}

}
```

## Call by reference

> 참조에 의한 호출

Call by reference 호출 방식은 함수 호출 시 인자로 전달되는 변수의 레퍼런스를 전달한다. C/C++ 의 경우 변수의 주소 값 자체를 가져올 수 있으므로 참조에 의한 호출이 가능하다.

-  따라서 함수 안에서 인자 값이 변경되면, argument로 전달된 객체의 값도 변경된다.

### 예시 (C code)

```C
**#include <stdio.h>**

void func(int *n) { //포인터 변수를 이용하여, main의 변수 a를 가리킨다
    *n = 20; // 포인터 n이 가리키는 값을 20으로 변경
}

void main() {
    int n = 10;
    func(&n);
    printf("%d", n); // 20
}
```

아래의 예제 코드를 확인해보자.

```java
public class Main {

	static class User {
	
		String name;
		
		public User(String name) {
			this.name = name;
		}
	}

	public static void main(String[] args) {
	
		User a = new User("Kim"); // 1
		
		foo(a);
	
	}

	static void foo(User b) { // 2
	
		b = new User("Park"); // 3
	
	}
}
```

1. a에 User 객체 생성 및 할당(새로 생성된 객체의 주소 값을 가지고 있음)
	-  a -> User Object [name = "Kim"]
2. b라는 파라미터에 a가 가진 주소 값을 복사하여 가진다.
	-  a -> User Object [name = "Kim"]
	-  b -> ↑
3. 새로운 객체를 생성하고 새로운 생성된 주소 값 b를 가지며 a는 그대로 원본 객체를 가리킨다.
	-  a -> User Obejct [name = "Kim"]
	-  b -> User Object [name = "Park"]

파라미터에 객체/값의 주소 값을 복사하여 넘겨주는 방식을 사용하고 있는 Java는 주소 값을 넘겨 주소 값에 저장되어 있는 값을 사용하는 Call by reference로 오해할 수 있어 주의해야 한다.