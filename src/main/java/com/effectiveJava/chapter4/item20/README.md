# item 20. 추상 클래스보다는 인터페이스를 우선하라

> 다중 구현용 타입으로는 인터페이스가 가장 적합하다.

## 인터페이스 다중 구현의 장점

- 단일 상속의 한계
- 기존 클래스에도 손쉽게 새로운 인터페이스를 구현해 넣을 수 있다
- 믹스인 정의에 안성맞춤

### 자바 단일 상속의 한계

자바가 제공하는 다중 구현 메커니즘은 인터페이스와 **추상클래스**이다.  
둘의 큰 차이는 **추상클래스가 정의한 타입을 구현한 클래스는 반드시 추상 클래스의 하위클래스라는 것이다.**   
반면, 인터페이스틑 구현한 채로 다른 어떤 클래스 / 인터페이스를 상속할 수 있다.

## 기존 클래스에도 손쉽게 새로운 인터페이스를 구현해 넣을 수 있다

1. 인터페이스가 요구하는 메서드 추가
2. implements 추가하면 끝

Java platform의 Comparable, Iterable, AutoCloseable 인터페이스가 추가될 당시에도 그랬다.

### 기존 클래스에 새로운 추상 클래스를 끼워넣기는 어렵다

- 새로운 추상클래스를 추가하면 기존 클래스의 모든 자손은 추상클래스의 자손이 됨
- 적절치 않은 상황에도 강제로

## 인터페이스는 믹스인 <sup>mixin</sup> 정의에 안성맞춤이다

- 믹스인 : 클래스의 주된 기능에 선택적 기능을 혼합 하는 것
- 믹스인 이점 : '주된 타입' 외에도 특정 선택적 행위를 제공한다고 선언
- ex. Comparable을 구현한 클래스는 인스턴스의 순서를 정할 수 있다고 선언하는 것

## 인터페이스로는 계층구조가 없는 타입 프레임워크를 만들 수 있다

- 현실세계에서 계층 구조를 엄격하게 구분하기 어려운 경우 적합
- 추상클래스로 구조를 표현하려면 combinatorial explosion <sup>조합 폭발</sup> 발생
    - sing() 클래스, act() 클래스, singAndAct() 클래스

```java

public interface Idol {
    void sing();
}

public interface Actor {
    void act();
}

public interface IdolActor extends Idol, Actor {
    void sing();

    void act();
}
```

## 래퍼클래스 관용구 <sup>item 18</sup>와 사용하면 인터페이스는 기능 향상의 안전하고 강력한 수단이 된다

타입을 추상클래스로 정의하면 타입에 기능 추가를 위해선 상속해야한다.  
상속해서 만든 클래스는 인터페이스보다 활용도가 떨어지고 깨지기 쉽다.

## 인터페이스 작성 Tips

### 인터페이스 메서드 중 구현방법이 명확하다면 디폴트메서드로 제공하자

- 구현클래스를 작성할 프로그래머의 일감이 덜어진다
- 디폴트 메서드를 제공할 떄는 @implSpec 자바독 태그를 사용해 문서화하자
- eqauls, hashCode, compareTo 등의 메서드는 디폴트 메서드로 제공하면 안된다

```java
public interface Connector {
    /**
     * @implSpec 이 구현체는 다음과 같은 과정을 거쳐 연결한다.
     * */
    default void connect() {
        //... (상세 구현 생략)
        System.out.println("미리 구현해두었으니 그대로 사용하세요.");
    }
}
```

### skeletal implementation <sup>추상 골격 구현</sup> 클래스를 제공하자

- 인터페이스와 추상 클래스의 장점을 모두 취하는 방법
- template method pattern <sup>템플릿 메서드 패턴</sup>
- ex. AbstractCollection, AbstractSet, AbstractList
- simulate multiple inheritance <sup>다중 상속</sup>
    - 골격 구현 클래스를 우회적으로 이용하는 방법
    - 인터페이스 구현 클래스에서 골격 구현을 확장한 private 내부 클래스를 정의하고,
    - 인터페이스 메서드들이 이 클래스의 메서드를 호출하게 한다.

> 스켈레톤 구현 클래스명 관례  
> 인터페이스 이름이 Interface라면 구현 클래스는 AbstractInterfaceq로 짓는다.

#### 기본 구조

1. 인터페이스로 타입과 디폴트 메서드 정의 <sup>골격 정의</sup>
2. 구현 클래스로 나머지 메서드 구현 <sup>골격 구현</sup>

#### 골격 구현 클래스 작성법

1. 인터페이스를 살펴 기반 메서드 선정 <sup>다른 메서드들의 구현에 사용되는 메서드</sup>
    - 선정한 기반 메서드가 골격 구현의 추상 메서드로 지정
2. 기반 메서드들을 사용해 직접 구현 가능한 메서드는 디폴드 케서드로 제공
    - 단, equals, hashCode, compareTo는 디폴트 메서드로 제공하면 안됨
3. 만약 인터페이스의 메서드 모두가 기반 메서드이자 디폴트 메서드라면 골격 구현 클래스는 필요 없음
4. 기반메서드나 디폴트 메서드로 만들지 못한 메서드들은 골격 구현 클래스에 작성
5. 상속해서 사용하는 것이 기본이므로 상속 대비 설계 및 문서화 지침 준수 <sup>[item 19](../item19)</sup>

#### ex. Map.Entry 인터페이스

- 기반 메서드 : getKey, getValue
- Object 메서드들은 골격 구현 클래스에서 구현

##### Map.Entry 인터페이스의 골격 구현 클래스 AbstractMapEntry

```java
public abstract class AbstractMapEntry<K, V> implements Map.Entry<K, V> {

    @Override
    public V setValue(V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        if (!(o instanceof Map.Entry)) {
            return false;
        }
        Map.Entry<?, ?> e = (Map.Entry) o;
        return Objects.equals(e.getKey(), getKey()) && Objects.equals(e.getValue(), getValue());
    }
}
```

##### 골격 구현  AbstractList 를 이용해 완성한 구체 클래스 Foo

```java
public class Foo {
    /**
     * int 배열을 Integer List로 변환하는 메서드
     * */
    static List<Integer> intArrayAsList(int[] a) {

        // 골격 구현 AbstractList 사용
        return new AbstractList<Integer>() {
            @Override
            public Integer get(int index) {
                return a[index]; // 오토박싱(아이템 6)
            }

            @Override
            public Integer set(int index, Integer element) {
                int oldVal = a[index];
                a[index] = element; // 오토언박싱
                return oldVal; // 오토박싱
            }

            @Override
            public int size() {
                return a.length;
            }
        };
    }
}
```

#### simple implementation <sup>단순 구현</sup>

- 골격 구현의 작은 변종
- ex. AbstractMap.SimpleEntry
- 골격 구현 클래스가 추상클래스가 아님
- 동작하는 골격 구현 클래스
- 그대로 써도 되고 필요에 따라 확장해도 됨