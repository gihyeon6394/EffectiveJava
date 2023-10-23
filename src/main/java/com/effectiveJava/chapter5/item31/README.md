# item 31. 한정적 와일드카드를 사용해 API 유연성을 높이라

> 한정적 와일드카드를 추가하자. 복잡하면 PECS를 사용하자

- 매개변수화 타입은 불공변 (invariant)
- 즉, `List<String>`은 `List<Object>`의 하위 타입이 아님
    - `List<String>`은 `List<Object>` 로 할 수 있는 일을 다 할 수는 없음

## 한정적 와일드 카드로 API 유연성 높이기

````
public void pushAll(Iterable<E> src){
    for(E e : src){
        push(e);
    }
}

@Test
@DisplayName("test")
public void tst() {
    Stack<Number> numberStack = new Stack<>();
    Iterable<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
    numberStack.pushAll(integers); // compile err : error: incompatible types: Iterable<Integer> cannot be converted to Iterable<Number>
}
````

- `Stack<Nubmer>`는 `Stack<Integer>`의 상위 타입도, 하위 타입도 아님

````
// public void pushAll(Iterable<E> src){
public void pushAll(Iterable<? extends E> src) {
    for (E e : src) {
        push(e);
    }
}

// public void popAll(Collection<E> dst){
public void popAll(Collection<? super E> dst) {
    while (!isEmpty()) {
        dst.add(pop());
    }
}

@Test
@DisplayName("pushAll")
public void tstPushAll() {
    Stack<Number> numberStack = new Stack<>();
    Iterable<Integer> integers = Arrays.asList(1, 2, 3, 4, 5);
    numberStack.pushAll(integers); // compile err : error: incompatible types: Iterable<Integer> cannot be converted to Iterable<Number>
}

@Test
@DisplayName("popAll")
public void tstPopAll() {
    Stack<Number> numberStack = new Stack<>();
    numberStack.push(1);
    numberStack.push(2);
    numberStack.push(3);

    Collection<Object> objects = new ArrayList<>();
    numberStack.popAll(objects);
}

````

- `? extends E` : 가능한 매개변수화 타입을 `E`의 하위 타입으로 한정
- `? super E` : 가능한 매개변수화 타입을 `E`의 상위 타입으로 한정

## PECS : producer-extends, consumer-super

- 매개변수화 한정적 와일드 카드 공식
- `T`를 생산한다면, `<? extends T>`를 사용하고, `T`를 소비한다면, `<? super T>`를 사용하라
- Get and Put Principle : Naftalin and Wadler가 PECS를 부르는 원칙
    - Get : `extends`
    - Put : `super`

````
// E는 생산자 : return 타입
//  public static <E> Set<E> union(Set<E> s1, Set<E> s2) {
public static <E> Set<E> union(Set<? extends E> s1, Set<? extends E> s2) {
    Set<E> result = new HashSet<E>(s1);
    result.addAll(s2);
    return result;
}

@Test
@DisplayName("한정적 와일드 카드 사용하기")
public void ts1() {
    Set<Integer> integers = Set.of(1, 2, 3);
    Set<Double> doubles = Set.of(1.1, 2.2, 3.3);
    /**
     *   required: Set<E>,Set<E>
     *   found: Set<Integer>,Set<Double>
     *   reason: inference variable E has incompatible equality constraints Double,Integer
     *   where E is a type-variable:
     *     E extends Object declared in method <E>union(Set<E>,Set<E>)
     *
     */
    Set<Number> numbers = union(integers, doubles);
}
````

````
// E는 생산자 : 최댓값을 생산
//  public static <E extends Comparable<E>> E max(List<E> list) {
public static <E extends Comparable<? super E>> E max(List<? extends E> list) {
    if (list.isEmpty()) {
        throw new IllegalArgumentException("빈 집합");
    }

    E result = null;
    for (E e : list) {
        if (result == null || e.compareTo(result) > 0) {
            result = e;
        }
    }

    return result;
}

@Test
@DisplayName("한정적 와일드 카드 사용하기")
public void tst3() {
    List<String> aespa = List.of("Winter", "Karina", "Ningning", "Giselle");
    System.out.println(max(aespa));

    List<Integer> ages = List.of(20, 21, 22, 23, 24);
    System.out.println(max(ages));
}
````

- 매개변수 `List<? extends E> list` : `list`는 `E` 인스턴스를 생산
- 타입 매개변수 `<E extends Comparable<? super E>>` : `Comparable<E>`는 `E` 인스턴스를 소비
    - 일반적으로 `Comparable<T>` 보다는 `Comparable<? super T>`를 사용하는 것이 좋음

```java

package java.util.concurrent;

public interface ScheduledFuture<V> extends Delayed, Future<V> {
    // ...
}

package java.util.concurrent;

public interface Delayed extends Comparable<Delayed> {
    // ...
}

package java.lang;

public interface Comparable<T> {
    // ...
}
```

````
public static <E extends Comparable<E>> E max(List<E> list) { ...}

/**
  *   required: List<E>
  *   found: List<ScheduledFuture<?>>
  *   reason: inference variable E has incompatible equality constraints Delayed,ScheduledFuture<?>
  *   where E is a type-variable:
  *     E extends Comparable<E> declared in method <E>max(List<E>)
* */
List<ScheduledFuture<?>> scheduledFutures = List.of(....
System.out.println(max(scheduledFutures));
````

- `ScheduledFuture` 는 `Comparable<ScheduledFuture>`를 구현하지 않음
- `ScheduledFuture` 는 `Delayed`를 확장하고, `Delayed`는 `Comparable<Delayed>`를 확장

### 반환타입에는 와일드 카드 타입을 쓰지 말자

````

public static <E> Set<? extends E> union(Set<? extends E> s1, Set<? extends E> s2) {
    Set<E> result = new HashSet<E>(s1);
    result.addAll(s2);
    return result;
}

@Test
@DisplayName("한정적 와일드 카드 사용하기")
public void ts1() {
    Set<Integer> integers = Set.of(1, 2, 3);
    Set<Double> doubles = Set.of(1.1, 2.2, 3.3);
    Set<? extends Number> numbers = union(integers, doubles);

}
````

- 유연성을 높여주기는 커녕, 클라이언트 코드도 와일드카드를 명시해야하게 만듦
- **클라이언트 코드에서 와일드카드를 신경써야한다면, API에 문제가 있는 것**

### 메서드 선언에 타입 매개변수가 한번만 나오면 와일드 카드로 대체하라

````
// list의 index i와 j를 swap
public static <E> void swap(List<E> list, int i, int j) {}
public static void swap(List<?> list, int i, int j) {}
````

- 신경 쓸 타입 매개변수 없음

````
// 실제 구현

public static void swap(List<?> list, int i, int j) {
    // list.set(i, list.set(j, list.get(i))); // compile err : List<?>에는 null 외에는 어떤 값도 넣을 수 없다.
    swapHelper(list, i, j);
}

// 와일드 카드 타입을 실제 타입으로 바꿔주는 private 도우미 메서드
private static <E> void swapHelper(List<E> list, int i, int j) {
    list.set(i, list.set(j, list.get(i)));
}
````

---

## 매개변수 (parameter) vs 인수 (argument)

```
void add(int value) {...}
add(10);
```

- 매개변수 : 메서드에서 정의한 **변수**
    - `value`
- 인수 : 메서드를 호출할 때 전달한 **값**
    - `10`