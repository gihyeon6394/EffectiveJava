# item 21. 인터페이스는 구현하는 쪽을 생각해 설계하라

> 인터페이스가 공개되고 난 뒤 디폴트 메서드 추가를 하지 마라. 새로운 인터페이스 작성 시에는 디폴트 메서드 유용하다.

## 디폴트 메서드 추가 시 주의할 점

```java

public class Item21 {
    private interface Interface1 {
        void method1();

        default void method2() {
            System.out.println("Interface1 method2");
        }
    }

    private class Class1 implements Interface1 {
        @Override
        public void method1() {
            System.out.println("Class1 method1");
        }
    }
}
```

- 디폴트 메서드 :  java 8 부터 기존 구현체를 깨뜨리지 않고 인터페이스에 메서드 추가 가능
- 디폴트 메서드가 **기존 구현체들과 호환 잘되리라는 보장 없음**
    - 디폴트 메서드를 선언하면 구현체들이 디폴트 구현을 쓰게 됨
    - java 7까지의 구현체들은 **인터페이스에 메서드 추가는 없다는 전제**로 프로그래밍 했두었음
- ex. Collection 인터페이스에 추가된 removeIf 메서드
    - 현존하는 모든 Collection 구현체와는 어울리지 못함
- 디폴트 메서드는 컴파일 타임에는 오류가 없어도 런타임 오류를 일으킬 가능성이 있음

### 기존 인터페이스에 디폴트 메서드를 추가하는 일은 피하자

- 꼭 필요한 경우가 아니면 말이다
- 추가하려는 티폴트 메서드가 기존 구현체와 충돌하지 않을지 심사숙고해야함

### 새로운 인터페이스 작성 시 디폴트 메서드는 유용하다

- 표준 메서드 작성을 디폴트 메서드를 이용해서 할 수 있고,
- 구현체를 작성할 클라이언트의 수고를 덜어준다


