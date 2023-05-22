# item 20. 추상 클래스보다는 인터페이스를 우선하라

> 다중 구현용 타입으로는 인터페이스가 가장 적합하다.

## 인터페이스 다중 구현의 장점

- 자바 단일 상속의 한계
- 기존 클래스에도 손쉽게 새로운 인터페이스를 구현해 넣을 수 있다
- 믹스인 정의에 안성맞춤
- 계층구조 없이 타입 프레임워크 생성 가능

### 자바 단일 상속의 한계

자바가 제공하는 다중 구현 메커니즘은 인터페이스와 **추상클래스**이다.  
**추상클래스가 정의한 타입을 구현한 클래스는 반드시 추상 클래스의 하위 클래스여야한다.**   
반면, 인터페이스틑 구현한 채로 다른 어떤 클래스 / 인터페이스를 상속할 수 있다.  
**즉 다중 상속이 가능해진다.**

### 기존 클래스에도 손쉽게 새로운 인터페이스를 구현해 넣을 수 있다

1. 기존 클래스가 요구하는 인터페이스와 메서드 작성
2. 기존 클래스에 implements 추가

ex. Java에 Comparable, Iterable, AutoCloseable 인터페이스가 추가될 당시

#### 기존 클래스에 새로운 추상 클래스를 끼워넣기는 어렵다

- 새로운 추상클래스를 상속시키면 기존 클래스의 모든 자손은 추상클래스의 자손이 됨
- 기존 클래스가 상속 관계에 있다면 다중 상속 불가능

### 인터페이스는 믹스인 <sup>mix-in</sup> 정의에 안성맞춤이다

- 믹스인 : 클래스의 주된 기능에 선택적 기능을 혼합 하는 것
- 믹스인 이점 : '주된 타입' 외에도 특정 선택적 행위를 제공한다고 선언
- ex. Comparable을 구현한 클래스는 인스턴스의 순서를 정할 수 있다고 선언하는 것

### 인터페이스로는 계층 구조가 없는 Type 만들 수 있다

- 현실세계에서 계층 구조를 엄격하게 구분하기 어려운 경우 적합
- 추상클래스로 구조를 표현하려면 combinatorial explosion <sup>조합 폭발</sup> 발생

#### Type 프레임워크 예시 (인터페이스)

```java
// Idol 타입
public interface Idol {
    void sing();
}

// Actor 타입
public interface Actor {
    void act();
}

// IdolActor 타입
public interface IdolActor extends Idol, Actor {
    void musical();
}
```

#### 조합 폭발

```java
public abstract class Idol {
    abstract void sing();
}

public abstract class Actor {
    abstract void act();
}

public abstract class IdolActor {
    abstract void sing();
    abstract void act();
    abstract void musical();
}
```

## 래퍼클래스 관용구 <sup>[item 18](../item18/README.md)</sup>와 사용하면 인터페이스는 기능 향상의 안전하고 강력한 수단이 된다

타입을 추상클래스로 정의하면 타입에 기능 추가를 위해선 상속해야한다.  
상속해서 만든 클래스는 인터페이스보다 활용도가 떨어지고 깨지기 쉽다.

## 인터페이스 작성하기

### 인터페이스 메서드 중 구현 방법이 명확하다면 디폴트 메서드로 제공한다

- 구현 클래스를 작성할 프로그래머의 일감이 덜어진다
- 디폴트 메서드를 제공할 때는 @implSpec 자바독 태그를 사용해 문서화하자
- eqauls, hashCode, compareTo 등의 메서드는 디폴트 메서드로 제공하면 안된다

```java
public interface Idol {
    /**
     * @ImplSpec 이 메서드는 사람이 수면에 들어가는 것을 나타낸다.
     * */
    default void sleep(){System.out.println("사람 사는 거 다 똑같지. 사람은 이렇게 수면에 들어요.");}
}
```

### skeletal implementation <sup>추상 골격 구현</sup> 클래스를 제공하자

- 일반적인 interface 구현은 중복 코드 발생
- ex. practice, goAirport 중복
    ```java
    public interface Idol {
        void practice();
        void goAirport();
        void sing();
        void viewMyCompany();
    }
  
    // 클라이언트 코드 
    public class NewJeans implements Idol{
        @Override
        public void practice() { System.out.println("강남 연습실로 갑니다."); } // 중복
        @Override
        public void goAirport() { System.out.println("인천공항으로 갑니다."); } // 중복
        @Override
        public void sing() { System.out.println("play music ATTENTION"); }
        @Override
        public void viewMyCompany() { System.out.println("my company is HYBE"); }
    }
    
    public class Ive implements Idol{
        @Override
        public void practice() { System.out.println("강남 연습실로 갑니다."); } // 중복
        @Override
        public void goAirport() { System.out.println("인천공항으로 갑니다."); } // 중복
        @Override
        public void sing() { System.out.println("play music AFTER LIKE"); }
        @Override
        public void viewMyCompany() { System.out.println("my company is STARSHIP"); }
    }
    
    ```
  
- 인터페이스와 추상 클래스의 장점을 모두 취하는 방법
- template method pattern <sup>템플릿 메서드 패턴</sup>
- ex. AbstractCollection, AbstractSet, AbstractList

> 추상 골격 구현 클래스 명명 관례    
> 인터페이스 이름이 Interface라면 구현 클래스는 AbstractInterfaceq로 짓는다.

#### 기본 구조

1. 인터페이스로 타입과 디폴트 메서드 정의 <sup>골격 정의</sup>
2. 구현 클래스로 나머지 메서드 구현 <sup>골격 구현</sup>

#### 골격 구현 클래스 작성법

1. 인터페이스를 살펴 기반 메서드 선정 <sup>다른 메서드들의 구현에 사용되는 메서드</sup>
    - 선정한 기반 메서드를 골격 구현의 추상 메서드로 작성
2. 기반 메서드들을 사용해 직접 구현 가능한 메서드는 디폴드 메서드로 제공
    - 단, equals, hashCode, compareTo는 디폴트 메서드로 제공하면 안됨
3. 만약 인터페이스의 메서드 모두가 기반 메서드이자 디폴트 메서드라면 골격 구현 클래스는 필요 없음
4. 기반메서드나 디폴트 메서드로 만들지 못한 메서드들은 골격 구현 클래스에 작성
5. 골격 구현 클래스는 상속해서 사용하는 것이 기본이므로 상속 대비 설계 및 문서화 지침 준수 <sup>[item 19](../item19/README.md)</sup>

```java
public abstract class AbstractIdol implements Idol {
    @Override
    public void practice() {System.out.println("강남 연습실로 갑니다.");}  
    @Override
    public void goAirport() {System.out.println("인천공항으로 갑니다.");} 
}

// 클라이언트 코드
public class Ive extends AbstractIdol implements Idol {
    @Override
    public void sing() {System.out.println("play music AFTER LIKE");}

    @Override
    public void viewMyCompany() {System.out.println("my company is STARSHIP");}
}

public class NewJeans extends AbstractIdol implements Idol {
    @Override
    public void sing() {System.out.println("play music ATTENTION");}

    @Override
    public void viewMyCompany() {System.out.println("my company is HYBE");}
}
```

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

##### 골격 구현 클래스  AbstractList 를 이용해 완성한 구체 클래스 Foo

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

### simulate multiple inheritance <sup>시뮬레이트한 다중 상속</sup>

- 이미 상속받을 클래스가 다른 클래스를 상속받고있다면?
- 골격 구현 클래스를 우회적으로 이용하는 방법
- 인터페이스 구현 클래스에서 골격 구현 클래스를 확장한 private 내부 클래스를 정의하고,
- 인터페이스 메서드들이 이 클래스의 메서드를 호출하게 한다.

```java

public class SmEnt {
    public void showEntIdol(){System.out.println("소녀시대, 레드벨벳, 에스파, ...");}
}

// 이미 Aespa가 SmEnt 상속받고 있는 상황
// 어떻게 Idol 인터페이스를 구현할 수 있을까?
public class Aespa extends SmEnt implements Idol {

    private AbstractIdolInner abstractIdolInner = new AbstractIdolInner();

    @Override
    public void practice() {abstractIdolInner.practice();}

    @Override
    public void goAirport() {abstractIdolInner.goAirport();}

    @Override
    public void sing() {abstractIdolInner.sing();}

    @Override
    public void viewMyCompany() {abstractIdolInner.viewMyCompany();}
    // 골격 구현 클래스를 확장한 private 내부 클래스
    private class AbstractIdolInner extends AbstractIdol {
        @Override
        public void sing() {System.out.println("play music BLACK MAMBA");}

        @Override
        public void viewMyCompany() {System.out.println("my company is SM");}
    }
}
```

### simple implementation <sup>단순 구현</sup>

- 골격 구현의 작은 변종
- ex. AbstractMap.SimpleEntry
- 골격 구현 클래스가 추상클래스가 아님
- 동작하는 골격 구현 클래스
- 그대로 써도 되고 필요에 따라 확장해도 됨


``` java
public class AbstractIdolSolo implements Idol {
    @Override
    public void practice() { System.out.println("강남 연습실로 갑니다."); }

    @Override
    public void goAirport() { System.out.println("인천공항으로 갑니다."); }

    @Override
    public void sing() { System.out.println("play music 1위 앨범"); }

    @Override
    public void viewMyCompany() { System.out.println("i have no company, i'm solo"); }
}
```

