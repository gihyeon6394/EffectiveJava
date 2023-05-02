<h1>item 16. public 클래스에서는 public 필드가 아닌 접근자 메서드를 사용하라</h1>

> 가변필드라면 public 으로 공개하지 말 것     
> 불변필드면 public 이어도 상관없지만 여전히 불편함이 있음  
> public 클래스의 필드는 public 이 아니어야 한다

<h3>위험한 public class</h3>

~~~~
public class Point{
    public double x;
    public double y;
}
~~~~

- 내부 표현 수정 시 API를 수정해야함
- 불변식을 보장할 수 없음
- 외부에서 필드에 접근할 때 부수 작업을 수행할 수 없음

<h2>public class 필드의 접근제어를 private으로 낮추고 대신 접근자 메서드를 사용</h2>

- package-private, private 중첩 클래스라면 필드를 public으로 두어도 문제 없음
    - 접근자 방식보다 클라이언트 코드도 더 깔끔함
    ~~~~
    private static class Foo{
        public int a;
        public int b;
    }
    ...
    Foo foo = new Foo();
    foo.a = 1;
    ~~~~
- 필드가 **불변**이라면 public으로 두어도 되지만...
    - API를 변경하지 않고는 표현 방식을 바꿀 수 없고
    - 필드를 수정할 때 부수 작업을 수행할 수 없음
