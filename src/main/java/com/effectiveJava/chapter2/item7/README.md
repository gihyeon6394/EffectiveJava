<h1>item 7. 다 쓴 객체 참조를 해제하라</h1>

> 개발자가 메모리 관리를 직접 코드로 해주는 경우는 드무나, 필요한 경우 참조를 해제하여 GC Target으로 만들라

<h3>Stack 의 pop 메서드</h3>

~~~~
public Object pop() {
    if (size == 0) {
        throw new EmptyStackException();
    }
    Object result = elements[--size];
    elements[--size] = null; // pop 할 객체는 참조 해제
    return result;
}
~~~~

<h3>메모리 누수 <sup>memory leak</sup></h3>

- GC 활동량, memory 사용량이 늘어남에 따라 성능이 점차 저하됨
- 결국 OutOfmemroyError 혹은 디스크 페이징을 일으킴
- 해법 : 객체 참조를 해제 (NULL 처리)하여 GC Target 으로 만들라

<h2>객체 참조를 NULL 처리하는 경우는 **예외적인 경우**이다</h2>

<h4>GC 는 비활성 영역에서 참조하는 객체도 여전히 사용 중인 객체로 판단</h4>

- ex. Stack 클래스의 elements 의 pop 이후 안쓰는 인덱스 인스턴스
-

<h3>개발자가 코드로 메모리 관리를 해주어야할 때</h3>

- 일반적으로 자기 메모리를 직접 관리하는 클래스
- 캐시
    - WeakHashMap의 key로 사용
      ~~~~
          Integer gcTarget = 1; // 캐싱 대상
          WeakHashMap<Integer, String> weakHashMap = new WeakHashMap<>();
          weakHashMap.put(gcTarget, "나 이거 지울래!");
  
          gcTarget = null; // 참조 해제
          System.gc();
      ~~~~
- 리스너, 콜백
    - WeakHashMap 사용
