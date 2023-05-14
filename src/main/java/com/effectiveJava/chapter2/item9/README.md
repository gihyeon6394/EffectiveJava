<h1>item 9. try-finally 보다는 try-with-resources 를 사용하라</h1>

> try 절에서 반납할 자원이 있다면 **무조건** try-with-resources 를 사용한다  
> 자원 반납 쉽고, 코드 깔끔하고, 로깅 정보도 좋다


<h3>try-finally 가 구린 이유</h3>

- 반납 자원이 2개 이상이면 지저분해짐
- 예외가 두번 발생하면 마지막 예외가 첫번째 예외를 삼킴
    - ex. try, finally 두군데에서 발생 시 finally 에서 발생한 예외만 클라이언트가 알 수 있음

```java
public class foo {
  public static void io() {
    InputStream in = new FileInputStream("filePath");
    try {
      OutputStream out = new FileOutputStream("filePathDestination");
      try {
        // ...
      } finally {
        out.close(); // 반납 1
      }
    } finally {
      in.close(); // 반납 2
    }
  }
}
```

<h2>try-with-resources 가 좋은 이유</h2>

```java
public class foo {
  public static void io() {
    try (InputStream in = new FileInputStream("filePath");
         OutputStream out = new FileOutputStream("filePathDestination")) {
    // ...
    }
  }
}

```

- 가독성
- 디버깅이 쉬움
    - 중첩 예외 발생 시 suppressed 스택 사용 가능

<h3>선제조건 : 해당 클래스가 AutoCloseable 구현</h3>

- 많은 자바 라이브러리가 이미 구현해두었음
- 프로그래머는 반납 자원을 다루는 클래스 작성 시 반드시 AutoCloseable 구현할 것
