<h1>item 18. 상속보다는 컴포지션을 사용하라</h1>

> 상속은 상위클래스의 리스크를 떠안는다
> 컴포지션+래퍼 는 상위클래스의 기능을 확장하면서 리스크를 회피한다

<h3>상속 시 2가지를 물을 것</h3>

1. 확장하려는 클래스의 API에 아무런 결함이 없는가?
2. 결함이 당신 <sup>하위 클래스</sup> API까지 전파돼도 괜찮은가?

<h2>상속이 안전한 경우</h2>

- 상위클래스와 하위클래스가 동일 패키지 안에서 프로그래머가 통제
- 확장할 목적으로 **잘** 설계되고 **문서화**가 잘된 클래스
- 인터페이스 상속
    - 클래스가 인터페이스를 구현
    - or 인터페이스가 인터페이스를 확장
- 하위클래스가 상위클래스의 **'진짜'** 하위 타입
    - A를 확장하려하는 B 클래스가 **is-a** 관계인지 확인할 것 <sub>B는 A이다.</sub>

<h3>상속이 위험한 경우</h3>

- 구현 상속 : 패키지 경계를 넘어 다른 패키지의 구체클래스를 상속

<h2>상속이 위험한 이유</h2>

- **캡슐화** 깨뜨림
    - 상위클래스가 수정되면 하위클래스 동작에 영향이감
        - 만약 상위클래스 설계가 미숙해서 수정이 잦다면?
    - 상위 클래스의 내부구현을 불필요하게 노출
    - 상위 클래스의 API는 평생 내부 구현에 묶여 클래스의 성능 영원히 제한
- 메서드 재정의
    - 상위 클래스의 메서드 세부 동작을 잘 모름
        - ex. **자기 사용 여부**를 알 수 없음 java.util.HashSet
    - 상위 클래스 다음 릴리즈에서 동일한 기능의 메서드가 추가된다면?
        - 하위 클래스에서 재정의한 메서드는 무용지물
        - 보안 허점
        - ex. Hashtable, Vector
- 새로운 메서드를 정의
    - 상위클래스의 새로운 릴리즈가 나오면서 시그니처가 같은 새로운 메서드가 탄생하면 하위클래스는 컴파일조차 되지 않음
    - 상위클래스가 만족하는 규약을 애초에 만족하지 못했을 가능성

<h2>상속의 대안 :  컴포지션+전달</h2>

<h4>컴포지션 (구성, composition) + 전달 (forwarding)</h4>

- 기존클래스가 새로운 클래스의 구성요소로 쓰임
- 새로운 클래스를 만들고 private 필드로 상위클래스를 인스턴스로 참조
- 위임 <sup>delegation</sup>
- 새로운 클래스는 기존 클래스의 내부 구현방식에 의존하지 않음

```java
// 래퍼 클래스
public class InstrumentedSet<E> extends ForwardingSet<E> {
    private int addCount = 0;

    public InstrumentedSet(Set<E> s) {
        super(s);
    }

    @Override
    public boolean add(E e) {
        addCount++; // 확장
        return super.add(e);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        addCount += c.size(); // 확장
        return super.addAll(c);
    }

    public int getAddCount() {
        return addCount;
    }
}

// 전달 클래스
class ForwardingSet<E> implements Set<E> {

        private final Set<E> s; // 기존 클래스 참조

        public ForwardingSet(Set<E> s) {
            this.s = s;
        }
        
        // 전달 메서드
        @Override
        public int size() {
            return s.size();
        }
        
        // ... 생략

        @Override
        public void clear() {
            s.clear();
        }
    }
```

<h3>구성요소</h3>

- 전달 클래스 : private 필드로 기존 클래스의 인스턴스를 참조하고, 전달 메서드를 정의한 클래스
    - 전달 메서드 : 기존 클래스의 메서드와 동일한 내용을 수행하는 메서드
    - ex. Guava의 전달메서드
- 래퍼 클래스 : 전달 클래스를 상속하여 기존 클래스를 감싸는 클래스
    - Decorator 패턴 : 기존 클래스의 기능을 확장함

<h3>래퍼 클래스의 단점</h3>

- 거의 없음
- 콜백 프레임워크에 어울리지 않음
    - SELF 문제 :  내부 객체를 콜백으로 넘기면 콜백이 래퍼를 참조하지 못하고, 자신을 참조함
- 전달 메서드를 직접 작성하기 귀찮음






