### Set

- 입력 순서를 유지하지 않고, 데이터의 중복을 허용하지 않는다.
- 데이터에 null 값도 중복으로 들어갈 수 없다.
- 인덱스가 따로 존재하지 않는 자료구조로서 Iterator를 사용하여야 한다.

```java
Set<String> set = new HashSet<>();

Iterator<String> iterator = set.iterator();
while(iterator.hasNext()) {
	System.out.println("value : " + iterator.next());
}
```

### Set 인터페이스의 주요 구현체

1. **HashSet**
	1. 입력 순서를 보장하지 않는다.
	2. 데이터의 중복을 허용하지 않는다.
	3. TreeSet보다 삽입, 삭제가 빠르다.
2. **LinkedHashSet**
	1. 입력 순서를 보장한다.
	2. 데이터의 중복을 허용하지 않는다.
3. **TreeSet**
	1. 입력한 데이터 크기가 비교 가능한 경우 오름차순으로 정렬된다.
	2. 데이터의 중복을 허용하지 않는다.
	3. 입력하는 데이터가 사용자 정의 객체인 경우 Comparable 구현하여 정렬 기준 설정 가능하다.
	4. 데이터 삽입 삭제에는 시간이 걸리지만 검색, 정렬이 빠른 장점이 있다.

### Map

- Key & Value 구조이다.
- Key(키)는 입력 순서를 유지하지 않으며, 중복을 허용치 않는다.
- Value(값)은 중복을 허용한다.
- 인덱스가 따로 존재하지 않기 때문에 Iterator를 사용하여야 한다.

### Map 인터페이스의 주요 구현체

1. **HashMap**
	1. 중복 Key를 허용하지 않으며 Key는 입력 순서를 유지하지 않는다.
	2. Key와 Value의 값으로 Null을 허용한다.
	3. 동기화가 보장되지 않는다.
	4. 검색에 가장 뛰어난 성능을 보인다.
2. **HashTable**
	1. 동기화가 보장되어 병렬 프로그래밍이 가능하고 HashMap보다 처리속도가 느리다.
	2. Key와 Value값으로 Null을 허용하지 않는다.
3. **LinkedHashMap**
	1. Key에 대한 입력 순서를 보장한다.
4. **TreeMap**
	1. 이진 탐색 트리(Red-Black Tree)를 기반으로 키와 값을 저장한다.
	2. 입력한 Key 데이터의 크기가 비교 가능할 경우 오름차순으로 정렬되며 빠른 검색이 가능하다.
	3. 저장 시 정렬을 하기 때문에 시간이 다소 걸린다.
	4. 입력하는 데이터가 사용자 정의 객체인 경우 Comparable을 구현하여, 정렬 기준 설정 가능하다.