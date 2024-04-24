본문에선 힙의 개념과 힙의 삽입 및 삭제에 대해 알아보려고 한다.

힙은, 우선순위 큐를 위해 만들어진 자료구조이다.

먼저 우선순위 큐에 대해서 간략하게 알아보고자 한다.

**우선순위 큐** : 우선 순위의 개념을 큐에 도입한 자료구조
> 데이터들이 우선순위를 가지고 있으며, 우선순위가 높은 데이터가 먼저 나간다.

시뮬레이션 시스템, 작업 스케쥴링 혹은 수치해석 계산에 사용된다.

우선순위 큐는 배열, 연결리스트, 힙으로 구현되며 힙으로 구현할 때 가장 효율적이다.

힙의 삽입은 O(logn), 삭제는 O(logn)의 시간 복잡도를 가진다.

### Heap

힙은 완전 이진 트리의 일종이다. 즉, 여러 값 중 최대값과 최소값을 빠르게 찾아내도록 만들어진 자료구조이다.

반 정렬 상태이며 힙 트리는 중복된 값을 허용한다. (이진 탐색 트리는 중복 값을 허용하지 않음)

### 힙 종류

**최대 힙(max heap)**

부모 노드의 키 값이 자식 노드의 키 값보다 크거나 같은 완전 이진 트리

**최소 힙(min heap)**

부모 노드의 키 값이 자식 노드의 키 값보다 작거나 같은 완전 이진 트리

![](https://camo.githubusercontent.com/0272251e94fed523dc5e701e7d50e1a21b195f830accc767294d417c598b5ffb/68747470733a2f2f74312e6461756d63646e2e6e65742f6366696c652f746973746f72792f313730383446353034444139383935323134)

### 구현

힙을 저장하는 표준적인 자료구조는 배열이다.
구현을 쉽게 하기 위해서 배열의 첫번째 인덱스인 0은 사용되지 않는다.
특정 위치의 노드 번호는 새로운 노드가 추가되더라도 변하지 않는다.
예를 들어, 루트 노드 1의 오른쪽 노드번호는 항상 3이다.

**부모 노드와 자식 노드 관계**

-  왼쪽 자식 index = (부모 index) * 2
-  오른쪽 자식 index = (부모 index) * 2 + 1
-  부모 index = (자식 index) / 2

**최대 힙의 삽입**

1. 힙에 새로운 요소가 들어오면, 일단 새로운 노드를 힙의 마지막 노드에 삽입한다.
2. 새로운 노드를 부모 노드들과 교환한다.

**최대 힙의 삭제**

1. 최대 힙에서 최대값은 루트 노드이므로 루트 노드가 삭제된다. 최대 힙에서 삭제 연산은 곧 최대값 요소를 삭제하는 것이다.
2. 삭제된 루트 노드에는 힙의 마지막 노드를 가져온다.
3. 삽입과 반대의 과정으로 자식노드와 비교하여 자리를 교체한다. (좌, 우 노드와 비교하여 더 큰 값과 자리를 교체한다.)

**최대 힙 삽입 및 삭제 구현**
```java
public static class maxHeap {
	
	private ArrayList<Integer> heap;

	public maxHeap() {
		heap = new ArrayList<>();
		heap.add(1000000); // 인덱스 1부터 시작하기 위하여
	}

	public void insert(int val) {
		heap.add(val);
		int p = heap.size() - 1;
		// 루트까지 이동하며, 자식 노드가 더 클 경우 swap
		while(p > 1 && heap.get(p / 2) < heap.get(p)) {
			int tmp = heap.get(p / 2);
			heap.set(p/2, heap.get(p));
			heap.set(p, temp);

			p = p/2;
		}
	}

	public int delete() {

		if(heap.size() - 1 < 1) {
			return 0;
		}

		int deleteItem = heap.get(1); // 삭제할 루트 노드 값 저장

		// 루트에 마지막 값을 넣은 후 마지막 값 삭제
		heap.set(1, heap.get(heap.size() - 1));
		heap.remove(heap.size() - 1);

		int pos = 1; // 루트 인덱스
		while((pos*2) < heap.size()) { // 왼쪽 자식 시작
			int max = heap.get(pos * 2); // 왼쪽 자식 값
			int maxPos = pos * 2; // 왼쪽 자식 위치

			// 오른쪽 자식과 비교하여 min 값 갱신
			if (((pos * 2 + 1) < heap.size()) && max < heap.get(pos * 2 + 1)) {
				max = heap.get(pos * 2 + 1);
				maxPos = pos * 2 + 1;
			}

			// 기준 부모값보다 min이 크다면 break;
			if (heap.get(pos) < min) {
				break;
			}

			int tmp = heap.get(pos);
			heap.set(pos, heap.get(minPos));
			heap.set(minPos, tmp);
			pos = minPos;
		}

		return deleteItem;
	}
}
```
**최소 힙의 삽입**

1. 힙에 새로운 요소가 들어오면, 일단 새로운 노드를 힙의 마지막 노드에 삽입한다.
2. 새로운 노드를 부모 노드들과 교환한다.

**최소 힙의 삭제**

1. 최소 힙에서 최소값은 루트 노드이므로 루트 노드가 삭제된다. 최소 힙에서 삭제 연산은 곧 최소값 요소를 삭제하는 것이다.
2. 삭제된 루트 노드에는 힙의 마지막 노드를 가져온다.
3. 삽입과 반대의 과정으로 자식노드와 비교하여 자리를 교체한다. (좌, 우 노드와 비교하여 더 작은 값과 자리를 교체한다.)

**최소 힙 삽입 및 삭제 구현**
```java
public static class minHeap {
	
	private ArrayList<Integer> heap;

	public maxHeap() {
		heap = new ArrayList<>();
		heap.add(0); // 인덱스 1부터 시작하기 위하여
	}

	public void insert(int val) {
		heap.add(val);
		int p = heap.size() - 1;
		// 루트까지 이동하며, 자식 노드가 더 작을 경우 swap
		while(p > 1 && heap.get(p / 2) > heap.get(p)) {
			int tmp = heap.get(p / 2);
			heap.set(p/2, heap.get(p));
			heap.set(p, temp);

			p = p/2;
		}
	}

	public int delete() {

		if(heap.size() - 1 < 1) {
			return 0;
		}

		int deleteItem = heap.get(1); // 삭제할 루트 노드 값 저장

		// 루트에 마지막 값을 넣은 후 마지막 값 삭제
		heap.set(1, heap.get(heap.size() - 1));
		heap.remove(heap.size() - 1);

		int pos = 1; // 루트 인덱스
		while((pos*2) < heap.size()) { // 왼쪽 자식 시작
			int min = heap.get(pos * 2); // 왼쪽 자식 값
			int minPos = pos * 2; // 왼쪽 자식 위치

			// 오른쪽 자식과 비교하여 min 값 갱신
			if (((pos * 2 + 1) < heap.size()) && min > heap.get(pos * 2 + 1)) {
				min = heap.get(pos * 2 + 1);
				minPos = pos * 2 + 1;
			}

			// 기준 부모값보다 min이 크다면 break;
			if (heap.get(pos) < min) {
				break;
			}

			int tmp = heap.get(pos);
			heap.set(pos, heap.get(minPos));
			heap.set(minPos, tmp);
			pos = minPos;
		}

		return deleteItem;
	}
}
```