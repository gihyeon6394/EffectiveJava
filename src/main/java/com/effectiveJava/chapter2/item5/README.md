<h1>item 5. 자원을 직접 명시하지 말고 의존 객체 주입을 사용하라</h1>

> 의존 자원에 따라 동작이 달라진다면, 의존 자원을 주입해라

<h2>사용하는 자원에 따라 동작이 달라지는 클래스</h2>

- **정적 유틸리티 클래스, 싱글턴 방식은 구리다**
- 자원 <sup>필드</sup>을 교체하는 메서드는 불완전하다
    - 어색하고, 오류 발생 가능성, thread-not-safe

```java
    public class ConnectionUtils {
    public static Connector connector = new Connector();

    public static void changeConnector(Connector connector) {
        ConnectionUtils.connector = connector;
    }
}
```

<h3>인스턴스를 사용할 때 <sup>**생성자**</sup> 필요한 자원을 넘겨준다</h3>

- Dependency Injection, DI의 한 형태
    - 생성자, 정적 팩터리, 빌더에 응용 가능
        ```java
       public class ConnectionUtils {
            public static Connector connector = new Connector();
    
            public ConnectionUtils(Connector connector) {
                this.connector = connector;
            }
      
            public valueOf(Connector connector) {
                this.connector = connector;
                return this;
            }
      
           public Builder connector(Connector connector) {
              this.connector = connector;
              return this;
           }
        }
        ```
- 생성자에 자원 팩터리를 넘겨줄 수 있음
    - 팩터리 메서드 패턴
    - 호출할 때마다 다른 타입의 인스턴스 생성 가능
    - Java 8의 Supplier<T>

<h4>DI는 의존성이 수천개가 되는 프로젝트에서는 코드가 복잡해짐</h4>

- 의존성 주입 프레임워크를 적절히 사용 spring framework, Guice 등


