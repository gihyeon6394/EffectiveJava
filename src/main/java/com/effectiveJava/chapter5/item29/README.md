# item 29. 이왕이면 제네릭 타입으로 만들라

> 제네릭을 사용하면 사용자가 형변환 없이 사용할 수 있으니 새로 설계할 때나 기존 설계에서 개선할 때는 제네릭을 사용하자

## 제네릭 클래스 만들기

```java
/**
 * 제네릭이 적용되기 이전 Stack
 * */
public class Stack {
    private Object[] elements; // stack element
    private int size; //stack size

    private static final int DEFAULT_INITIAL_CAPACITY = 16; // stack default size

    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object element) {
        ensureCapacity();
        elements[size++] = element;
    }


    public Object pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        Object result = elements[--size];
        elements[size] = null;
        return result;
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }

    }
}
```

```java

public class Stack<E> {
    private E[] elements; // stack element
    private int size; //stack size

    private static final int DEFAULT_INITIAL_CAPACITY = 16; // stack default size

    public Stack() {
        elements = new E[DEFAULT_INITIAL_CAPACITY]; // compile err : java: generic array creation
    }

    public void push(E element) {
        ensureCapacity();
        elements[size++] = element;
    }


    public E pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
        E result = elements[--size];
        elements[size] = null;
        return result;
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }

    }
}
```

- 실체화 불가 타입 `E`로는 배열을 만들 수 없음

### 해결책 1 : Object[]로 만들고 제네릭으로 형변환

````
elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];

compile result 
java: unchecked cast
  required: E[]
  found:    java.lang.Object[]
````

- `E[]`로 형변한 하면 경고를 보냄
- 타입 안전하지 않음
- 그러나 해당 배열은 private 필드로 직접 접근이 안되기 때문에 타입 안정하다고 볼 수 있음
- 따라서 `@SuppressWarnings("unchecked")`를 사용하여 경고를 숨김

````
@SuppressWarnings("unchecked")
public Stack() {
    elements = (E[]) new Object[DEFAULT_INITIAL_CAPACITY];
}
````

### 해결책 2 : elements 필드의 타입은  Object[]

```java
public class Stack<E> {
    private Object[] elements; // stack element
    private int size; //stack size

    private static final int DEFAULT_INITIAL_CAPACITY = 16; // stack default size


    public Stack() {
        elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(E element) {
        ensureCapacity();
        elements[size++] = element;
    }


    public E pop() {
        if (size == 0) {
            throw new EmptyStackException();
        }
//        E result =  elements[--size];
        // push에서 E 타입만 허용하므로 이 형변환은 안전
        @SuppressWarnings("unchecked")
        E result = (E) elements[--size];
        elements[size] = null;
        return result;
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }

    }
}
```

### 해결책 1 과 2의 비교

| 해결책 1                          | 해결책 2         |
|--------------------------------|---------------|
| 가독성 좋음 <br/>짧은 코드<br/>현업이 더 선호 | 제네릭으로 형변환이 잦음 |

- 단 해결책 1의 경우 `E`가 `Object`가 아닐 경우
    - `elements`의 런타임 타입이 컴파일 타임마다 달라짐
    - 힙 오염 (heap pollution) 발생

## 제네릭 활용

````
@Test
@DisplayName("test")
public void tst() {
    Stack<String> stack = new Stack<>();
    stack.push("Karina");
    stack.push("Jenny");
    stack.push("Jisoo");
    stack.push("Winter");
    stack.push("Joy");
    stack.push("Giselle");

    while (!stack.isEmpty()) {
        // stack의 pop은 String을 반환하므로 String의 메서드를 사용할 수 있다. (String으로 형변환 필요 없음)
        System.out.println(stack.pop().toUpperCase());
    }
}
````

```bash
GISELLE
JOY
WINTER
JISOO
JENNY
KARINA
```

- 제네릭을 사용하여 `String` 명시했으므로
- 명시적 형변환 없이 `String`의 메서드를 사용할 수 있음

### 제네릭 제약

- 기본 타입은 사용할 수 없음
    - `int` -> `Integer`
    - `double` -> `Double`
    - `boolean` -> `Boolean`
- 의도적으로 제약하는 경우 : **bounded type parameter**
    - `E extends Delayed`
    - `E`는 `Delayed`의 하위 타입만 가능
      