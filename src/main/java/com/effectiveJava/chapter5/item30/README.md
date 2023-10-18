# item 30. 이왕이면 제네릭 메서드로 만들라

> 제네릭 메서드를 사용해 메서드 사용시 형변환이 필요없도록 하자

- 매개변수화 타입을 받는 정적 유틸리티 메서드는 보통 제네릭
    - e.g. `Collections`의 `binarySearch()`

```
// Raw Type - don't use!
public static Set union(Set s1, Set s2) {
    Set result = new HashSet(s1);
    result.addAll(s2);
    return result;
}
```

- Raw Type을 사용해 타입 안전하지 못함
- 제네릭을 사용해 타입 안전하게 만들 수 있음

````
public static <E> Set<E> union1(Set<E> s1, Set<E> s2) {
    Set<E> result = new HashSet<E>(s1);
    result.addAll(s2);
    return result;
}

@Test
@DisplayName("제네릭 메서드 사용하기")
public void tst() {
    Set<String> aespa = Set.of("Winter", "Karina", "Ningning", "Giselle");
    Set<String> redVelvet = Set.of("Irene", "Seulgi", "Wendy", "Joy", "Yeri");
    Set<String> result = union2(aespa, redVelvet);
    System.out.println(result);

    Set<Integer> ages = Set.of(20, 21, 22, 23, 24);
    Set<String> result2 = union2(aespa, ages); // compile err : inference variable E has incompatible equality constraints Integer,String
}
````

## Generic Singleton Factory

- 요청한 타입 매개변수에 맞게 매번 그 객체의 타입으로 바꿔주는 정적 팩터리

### 실습 : 항등함수 만들기 (identity function)

- 항등함수 : 입력값을 받아서 그대로 반환하는 함수

````
private static UnaryOperator<Object> IDENTITY_FN = (t) -> t;

// return UnaryOperator<T>
// T -> Object는 항상 안전
@SuppressWarnings("unchecked")
public static <T> UnaryOperator<T> identityFunction() {
    return (UnaryOperator<T>) IDENTITY_FN;
}

@Test
@DisplayName("제네릭 싱글턴 팩터리")
public void tst2() {
    // String 타입의 UnaryOperator
    UnaryOperator<String> sameString = identityFunction();
    String[] aespa = {"Winter", "Karina", "Ningning", "Giselle"};
    Arrays.stream(aespa).forEach(str -> System.out.println(sameString.apply(str)));

    // Number 타입의 UnaryOperator
    UnaryOperator<Number> sameNumber = identityFunction();
    Number[] numbers = {1, 2.0, 3L};
    Arrays.stream(numbers).forEach(num -> System.out.println(sameNumber.apply(num)));
}
````

## Recursive type bound (재귀적 타입 한정)

- 자기 자신이 들어간 표현식을 사용하여 타입 매개변수의 허용 범위를 한정
- 주로 타입의 자연적 순서를 정하는 Comparable 인터페이스와 함께 쓰임

```java
public interface Comparable<T> {
    int compareTo(T o);
}
```

- 거의 모든 타입은 자신의 타입과만 비교할 수 있음
- `Comparable<String>`으로 `String` 비교, `Comparable<Integer>`로 `Integer` 비교

````
// E 타입 Set의 원소 중 최대값 반환
// 타입 E는 Comparable<E>를 구현해야 함
public static <E extends Comparable<E>> E max(Set<E> s) {
    if (s.isEmpty()) {
        throw new IllegalArgumentException("빈 집합");
    }

    E result = null;
    for (E e : s) {
        if (result == null || e.compareTo(result) > 0) {
            result = e;
        }
    }
    return result;
}

@Test
@DisplayName("Recursive type bound")
public void tst3() {
    Set<String> aespa = Set.of("Winter", "Karina", "Ningning", "Giselle");
    System.out.println(max(aespa));

    Set<Integer> ages = Set.of(20, 21, 22, 23, 24);
    System.out.println(max(ages));
}
````

- `<E extends Comparable<E>>` : 타입 `E`는 자신과 비교할 수 있어야 함