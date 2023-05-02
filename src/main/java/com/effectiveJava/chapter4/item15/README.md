<h1>item 15. 클래스와 멤버의 접근 권한을 최소화하라</h1>

> 신중하게 public API를 설정하고 나머지는 은닉해라


<h2>외부 컴포넌트로부터 내부를 얼마나 숨겼느냐 <sup>정보 은닉, 캡슐화</sup></h2>

- API <sup>외부에 공개된 것, public</sup>를 통해서만 다른 컴포넌트와 소통할 것
- 컴포넌트를 독립시켜 개발, 테스트, 최적화, 리팩토링 등에서 장점을 취함

<h2>자바는 접근제어 메커니즘으로 은닉을 제공한다</h2>

- **모든 클래스와 멤버의 접근성을 최대한 좁혀놓을 것**
    - 소프트웨어가 올바르게 동작하는 한 가장 낮은 수준으로 접근성을 설정
- public API를 신중하게 선정
- 접근 제어 대상 : 클래스, 멤버, 모듈 <sup>java 9</sup>

<h3>클래스</h3>

- 패키지 외부에서 쓸 일이 없다면 package-private
- public 은 API 가 되고 하위 호환을 위해 영원히 관리해야함
- 클래스 안에서만 사용하는 클래스나 인터페이스는 private static 으로 중첩

<h3>멤버</h3>

1. 공개 API 를 작성
2. 그 외의 모든 멤버는 private으로 선언
3. 같은 패키지에서 접근 할 일이 있다면 package-private으로 풀어줌

- 권한을 자주 풀어준다? -> 컴포넌트를 더 분해해야하는지 판단
- public class 의 protected 멤버는 API
    - protected 멤버는 적을수록 좋음
- public 클래스의 인스턴스 필드는 public이 아니어야함
    - 필드의 불변을 보장 못함
    - 클래스 not-thread-safe
- **public** static final 배열 필드를 두거나 이 필드를 반환하는 접근자 메서드를 제공하면 안됨
  ~~~~
  // 이거 안됨
  public static final User[] USERS1 = {new User("카리나", 19), new User("강해린", 21)};
  private static final User[] USERS2 = {new User("카리나", 19), new User("강해린", 21)};
  
  // unmodifiableList 이용
  public static final List<User> USER2_LIST = Collections.unmodifiableList(Arrays.asList(USERS2));
  
  // clone 이용
  public static final User[] values() {
      return USERS2.clone();
  }
  ~~~~

<h3>모듈 <sup>java 9</sup></h3>

- module-info.java
- 모듈 접근 제어자는 주의해서 사용해라
- **당분간은 사용하지 않는 것이좋다**
- 모듈수준에서 접근 제어를 하기위해서는 할 게 많음
