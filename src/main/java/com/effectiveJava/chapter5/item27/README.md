# item 27. 비검사 경고를 제거하라

> 비검사 경고가 없어지도록 모두 조치하라, 확신할때는 `@SuppressWarnings("unchecked")`를 사용

```java
public class Item27 {

    public static void main(String[] args) {
        Set<Integer> integers1 = new HashSet(); // Unchecked assignment: 'java.util.HashSet' to 'java.util.Set<java.lang.Integer>'
        Set<Integer> integers2 = new HashSet<>(); // OK
    }
}
```

## 비검사 경고를 모두 제거해야하는 이유

- 비검사 경고가 모두 제거되면 이 프로그램은 타입 안정성이 보장된다.
- 즉, 런타임 중 `ClassCastException`이 발생할 일이 줄어든다.
- 비검사 경고 중 거짓 정보가 있다면, 진짜 경고는 파묻혀서 찾기 힘들어짐
- `-Xlint:unchecked` 옵션을 사용하면, 컴파일러가 경고를 더 자세히 해줌
  ````
  java: unchecked conversion
  required: java.util.Set<java.lang.Integer>
  found:    java.util.HashSet
  ````

## `@SuppressWarnings("unchecked")` : 안정적이라고 확신할 수 있는 비검사 경고는 숨기기

- `@SuppressWarnings` : 선언문에만 달 수 있음
- 경고를 제거할 수 없지만, 타입 안정성이 확신될 떄 사용
- 확신하지 않는데, 사용한다면 런타임에 `ClassCastException`이 발생 가능성
- `@SuppressWarnings("unchecked")` 애너테이션은 항상 **가능한 한 좁은 범위**에 적용
- 어노테이션 사용시, 타입안정성의 이유도 주석으로 남긴다.   

#### `java.util.ArrayList`의 `toArray` 메서드

```java
@SuppressWarnings("unchecked")
public <T> T[] toArray(T[] a) {
    if (a.length < size)
        // Make a new array of a's runtime type, but my contents:
        return (T[]) Arrays.copyOf(elementData, size, a.getClass());
    System.arraycopy(elementData, 0, a, 0, size);
    if (a.length > size)
        a[size] = null;
    return a;
}
```

```java
public <T> T[] toArray(T[] a) {
    if(a.length < size) {
        // 매개변수 배열의 타입과 생성한 타입이 무조건 같은 타입이므로 올바른 형변환임
        @SuppressWarnings("unchecked")
        T[] result = (T[]) Arrays.copyOf(elementData, size, a.getClass());
        return result;
    }
    System.arraycopy(elementData, 0, a, 0, size);
    if(a.length > size) {
        a[size] = null;
    }
    return a;
}
```

